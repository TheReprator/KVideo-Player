package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal
import dev.reprator.video.modals.VideoSource
import dev.reprator.video.platform.dash.models.AdaptationSetVideo
import dev.reprator.video.platform.dash.DashSegmentLoader
import dev.reprator.video.platform.dash.DashUrls
import dev.reprator.video.platform.dash.GrowingFileHttpServer
import dev.reprator.video.platform.dash.Http
import dev.reprator.video.platform.dash.LiveFragmentedMuxer
import dev.reprator.video.platform.dash.models.AdaptationSetAudio
import dev.reprator.video.platform.dash.models.Mapper
import dev.reprator.video.platform.dash.models.MpdInfo
import dev.reprator.video.platform.dash.models.RawResponseMpdInfo
import dev.reprator.video.platform.dash.models.SegmentTemplate
import javafx.scene.media.MediaView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File

class PlaybackStateControllerImplJvm() : PlayerController {

    override lateinit var player: VideoPlayer

    val mediaView by lazy {
        MediaView().apply {
            isPreserveRatio = true
            isSmooth = true
        }
    }

    override suspend fun setupPlayer(): Boolean {
        player = VideoPlayerJvmImpl(mediaView)
        return true
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        val src = initOptions.sources.first().src
        val body = DashSegmentLoader.downloadMpdSafe(src)
        val bodyParsed: MpdInfo = extractMpdInfo(body)

        scope.launch {
            startDashLiveMuxToJavaFx(src, bodyParsed) { httpUrl ->
                withContext(Dispatchers.Default) {
                    val videoSource = VideoSource(httpUrl, "")
                    player.changeMedia(videoSource)
                }
            }
        }
    }

    private fun extractMpdInfo(xml: String): MpdInfo {
        val xmlFormat = XML {
            autoPolymorphic = true
            defaultPolicy { ignoreUnknownChildren() }
        }
        val rawPojo = xmlFormat.decodeFromString<RawResponseMpdInfo>(xml)
        val result = Mapper.mapTo(rawPojo)
        return result
    }

    suspend fun startDashLiveMuxToJavaFx(
        mpdUrl: String,
        mpd: MpdInfo, // your parsed object
        onReady: suspend (String) -> Unit // callback with http url to give MediaPlayer
    ) = coroutineScope {
        val base = DashUrls.buildBaseUrl(mpdUrl)

        val videoSet = mpd.adaptationSets.filterIsInstance<AdaptationSetVideo>().first()
        val audioSet = mpd.adaptationSets.filterIsInstance<AdaptationSetAudio>().first()

        val videoRep = videoSet.representation.first()
        val audioRep = audioSet.representation.first()

        val vTpl = videoRep.segmentTemplate
        val aTpl = audioRep.segmentTemplate

        // total segment count (VOD)
        fun count(tpl: SegmentTemplate, totalSec: Float): Long {
            val segSec = tpl.duration.toDouble() / tpl.timescale.toDouble()
            return kotlin.math.floor(totalSec / segSec).toLong()
        }
        val videoCount = count(vTpl, mpd.totalDuration)
        val audioCount = count(aTpl, mpd.totalDuration)
        val totalCount = minOf(videoCount, audioCount)

        // init downloads
        val vInitUrl = DashUrls.initUrl(base, vTpl.initialization)
        val aInitUrl = DashUrls.initUrl(base, aTpl.initialization)
        val vInit = withContext(Dispatchers.IO) { Http.getBytes(vInitUrl) }
        val aInit = withContext(Dispatchers.IO) { Http.getBytes(aInitUrl) }

        // start muxer + http server
        val outFile = File.createTempFile("live_", ".mp4").apply { deleteOnExit() }
        val muxer = LiveFragmentedMuxer(outFile).also { it.start() }
        muxer.writeMergedInit(vInit, aInit)

        val http = GrowingFileHttpServer(outFile, 8787).also { it.start() }
        onReady("http://127.0.0.1:8787/live.mp4")

        // stream fragments
        val job = launch(Dispatchers.IO) {
            var i = maxOf(vTpl.startNumber, aTpl.startNumber).toLong()
            val vStart = vTpl.startNumber.toLong()
            val aStart = aTpl.startNumber.toLong()

            while (i < minOf(vStart, aStart) + totalCount) {
                try {
                    val vSegUrl = DashUrls.segUrl(base, vTpl.media, i)
                    val aSegUrl = DashUrls.segUrl(base, aTpl.media, i)

                    val vBytes = async { Http.getBytes(vSegUrl) }
                    val aBytes = async { Http.getBytes(aSegUrl) }

                    // Append both fragments for this timeline slot.
                    // Order usually doesn’t matter, but many players prefer video then audio.
                    muxer.appendFragment(vBytes.await())
                    muxer.appendFragment(aBytes.await())

                    i++
                } catch (e: Exception) {
                    // stop when we hit 404/EOF in VOD
                    if (e.message?.contains("HTTP 404") == true) break
                    delay(300) // backoff for transient
                }
            }
        }

        // When scope is cancelled/finished
        job.invokeOnCompletion {
            http.stop()
            muxer.stop()
        }
    }

}