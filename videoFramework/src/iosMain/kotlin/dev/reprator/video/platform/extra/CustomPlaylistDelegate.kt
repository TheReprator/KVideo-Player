@file:Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY", "FunctionName")

package dev.reprator.video.platform.extra

import dev.reprator.video.platform.extra.models.AdaptationSetAudio
import dev.reprator.video.platform.extra.models.AdaptationSetVideo
import dev.reprator.video.platform.extra.models.AudioRepresentation
import dev.reprator.video.platform.extra.models.Mapper
import dev.reprator.video.platform.extra.models.MpdInfo
import dev.reprator.video.platform.extra.models.RawResponseMpdInfo
import dev.reprator.video.platform.extra.models.VideoRepresentation
import kotlinx.cinterop.*
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.XML
import platform.AVFoundation.*
import platform.Foundation.*
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.roundToInt

private const val customPlaylistScheme = "cplp"
private const val httpsScheme = "https"
private const val dashExt = "mpd"
private const val hlsExt = "m3u8"
private const val badRequestErrorCode = 400

private class ParseError(message: String) : Throwable(message)

fun toCustomUrl(url: String): String {
    return customPlaylistScheme + url.drop(5).dropLast(3) + hlsExt
}

private fun toOriginalUrl(url: String): String {
    return httpsScheme + url.drop(4).dropLast(4) + dashExt
}

class CustomPlaylistDelegate : NSObject(), AVAssetResourceLoaderDelegateProtocol {
    private val customPlaylists = mutableMapOf<String, String>()

    private fun reportError(loadingRequest: AVAssetResourceLoadingRequest, errorCode: Int) {
        val nsError = NSError.errorWithDomain(NSURLErrorDomain, errorCode.toLong(), null)
        loadingRequest.finishLoadingWithError(nsError)
    }

    override fun resourceLoader(
        resourceLoader: AVAssetResourceLoader,
        shouldWaitForLoadingOfRequestedResource: AVAssetResourceLoadingRequest
    ): Boolean {
        val customUrl = shouldWaitForLoadingOfRequestedResource.request.URL
        val scheme = customUrl?.scheme ?: return false
        if (isCustomPlaylistSchemeValid(scheme)) {
            dispatch_async(dispatch_get_main_queue()) {
                handleCustomPlaylistRequest(shouldWaitForLoadingOfRequestedResource)
            }
            return true
        }

       return false
    }

    private fun isCustomPlaylistSchemeValid(scheme: String): Boolean {
        return scheme == customPlaylistScheme
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

    fun Float.toFixed(decimals: Int): String {
        val factor = 10.0.pow(decimals)
        val rounded = kotlin.math.round(this * factor) / factor
        return rounded.toString()
    }

    private fun createMediaPlaylist(mpdInfo: MpdInfo, url: String): List<String> {
        val header = "#EXTM3U\n#EXT-X-VERSION:6\n"
        val tail = "#EXT-X-ENDLIST"
        val mediaPlaylists = mutableListOf<String>()

        val baseUrl = url.substringBeforeLast("/")
            .replace(customPlaylistScheme, httpsScheme)

        var j = 0

        for (adaptationSet in mpdInfo.adaptationSets) {
            for (representation in adaptationSet.representation) {
                val segmentTemplate = when (representation) {
                    is AudioRepresentation -> representation.segmentTemplate
                    is VideoRepresentation -> representation.segmentTemplate
                }

                // Media sequence
                val firstSequence = segmentTemplate.startNumber.takeIf { it > 0 } ?: 1
                val startSequence = "#EXT-X-MEDIA-SEQUENCE:$firstSequence\n"

                // Playlist type
                val playlistType = "#EXT-X-PLAYLIST-TYPE:EVENT\n"

                // Initialization
                val mapInit = "#EXT-X-MAP:URI=\"$baseUrl/${segmentTemplate.initialization}\"\n"

                // Total duration and segment duration
                val totalDuration = mpdInfo.totalDuration
                val segmentDuration =
                    segmentTemplate.duration.toFloat() / segmentTemplate.timescale.toFloat()
                val numSegments = ceil(totalDuration / segmentDuration).toInt()

                var segmentUnit = ""
                var maxDuration = 1

                if (adaptationSet.mimeType.contains("video")) {
                    // Generate video segments
                    for (i in firstSequence..numSegments) {
                        var dur = segmentDuration
                        if (i == numSegments) {
                            dur = totalDuration - segmentDuration * (numSegments - 1)
                        }
                        val duration = dur.roundToInt()
                        maxDuration = maxOf(maxDuration, duration)

                        val inf = "#EXTINF:${dur.toFixed(3)}\n"
                        val mediaUrl = "$baseUrl/" + segmentTemplate.media.replace(Regex("\\$.*?\\$"), i.toString()) + "\n"
                        segmentUnit += inf + mediaUrl
                    }

                    val videoRep = representation as VideoRepresentation
                    val videoInfo = "#EXT-X-MLB-VIDEO-INFO:" +
                            "codecs=\"${videoRep.codecs}\"," +
                            "width=\"${videoRep.width}\"," +
                            "height=\"${videoRep.height}\"," +
                            "sar=\"1:1\"," +
                            "frame-duration=${segmentTemplate.timescale}\n"

                    val info = "#EXT-X-MLB-INFO:max-bw=${videoRep.bandwidth},duration=$totalDuration\n"
                    val maxSegmentDuration = "#EXT-X-TARGETDURATION:$maxDuration\n"

                    val output = header + maxSegmentDuration + startSequence +
                            playlistType + mapInit + segmentUnit + videoInfo + info + tail
                    val savedUrl = saveAsCustomPlaylist(url, j, output)
                    mediaPlaylists.add(savedUrl)
                    j += 1

                } else if (adaptationSet.mimeType.contains("audio")) {
                    // Generate audio segments
                    for (i in firstSequence..numSegments) {
                        var dur = segmentDuration
                        if (i == numSegments) {
                            dur = totalDuration - segmentDuration * (numSegments - 1)
                        }
                        val duration = dur.roundToInt()
                        maxDuration = maxOf(maxDuration, duration)

                        val inf = "#EXTINF:${dur.toFixed(3)}\n"
                        val mediaUrl = "$baseUrl/"  + segmentTemplate.media.replace(Regex("\\$.*?\\$"), i.toString()) + "\n"
                        segmentUnit += inf + mediaUrl
                    }

                    val audioRep = representation as AudioRepresentation
                    val audioInfo = "#EXT-X-MLB-AUDIO-INFO:" +
                            "codecs=\"${audioRep.codecs}\"," +
                            "audioSamplingRate=\"${audioRep.audioSamplingRate}\"\n"

                    val channelInfo = "#EXT-X-MLB-AUDIO-CHANNEL-INFO:" +
                            "schemeIdUri=\"${audioRep.audioChannelConfiguration.schemeIdUri}\"," +
                            "value=\"${audioRep.audioChannelConfiguration.value}\"\n"

                    val info = "#EXT-X-MLB-INFO:max-bw=${audioRep.bandwidth},duration=$totalDuration\n"
                    val maxSegmentDuration = "#EXT-X-TARGETDURATION:$maxDuration\n"

                    val output = header + maxSegmentDuration + startSequence +
                            playlistType + mapInit + segmentUnit + audioInfo + channelInfo + info + tail

                    val savedUrl = saveAsCustomPlaylist(url, j, output)   // <-- save & return URL
                    mediaPlaylists.add(savedUrl)
                    j += 1
                }
            }
        }
        return mediaPlaylists
    }


    private fun createMasterPlaylist(mpdInfo: MpdInfo, mediaPlaylists: List<String>): String {
        val header = "#EXTM3U\n#EXT-X-VERSION:6\n"
        val output = StringBuilder()

        var j = 0

        mpdInfo.adaptationSets.forEach { adaptationSet ->
            when (adaptationSet) {
                is AdaptationSetVideo -> {

                    for (rep in adaptationSet.representation) {
                        // Trick play filter (skip frameRate == "1")
                        if (rep.frameRate == "1") {
                            continue
                        }

                        // Build video EXT-X-STREAM-INF
                        val videoMasterInfo = buildString {
                            append("""#EXT-X-STREAM-INF:AUDIO="audio",""") // audio group reference
                            append("""CODECS="${rep.codecs}",""")
                            append("RESOLUTION=${rep.width}x${rep.height},")
                            append("FRAME-RATE=${rep.frameRate},")
                            append("BANDWIDTH=${rep.bandwidth}")
                        }

                        val videoMediaPlaylist = mediaPlaylists[j].substringAfterLast('/')
                        output.appendLine(videoMasterInfo)
                        output.appendLine(videoMediaPlaylist)

                        j++
                    }
                }

                is AdaptationSetAudio -> {
                    adaptationSet.representation.forEach { rep ->
                        val audioMaster = buildString {
                            append("#EXT-X-MEDIA:TYPE=AUDIO,DEFAULT=YES,")
                            append("""GROUP-ID="audio",""")
                            append("""NAME="${adaptationSet.lang.ifBlank { "Main" }}",""")
                            val audioMediaPlaylist = mediaPlaylists[j].substringAfterLast('/')
                            append("""URI="$audioMediaPlaylist"""")
                        }
                        output.insert(0, audioMaster + "\n")
                        j++
                    }
                }
            }
        }

        return header + output.toString()
    }

    @Throws(ParseError::class)
    private fun saveAsCustomPlaylist(url: String, index: Int, playlist: String): String {
        val dot = url.lastIndexOf(".")
        if (dot < 0) throw ParseError("invalidUrl")
        val playlistUrl = url.substring(0, dot) + "_$index.$hlsExt"
        customPlaylists[playlistUrl] = playlist
        return playlistUrl
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    private fun respondWithPlaylist(loadingRequest: AVAssetResourceLoadingRequest, playlistBytes: ByteArray) {
        val cir = loadingRequest.contentInformationRequest
        cir?.let {
            it.contentType = "application/vnd.apple.mpegurl"
            it.contentLength = playlistBytes.size.toLong()
            it.byteRangeAccessSupported = false
        }

        val nsData: NSData = playlistBytes.usePinned { pinned ->
            NSData.create(bytes = pinned.addressOf(0), length = playlistBytes.size.toULong())
        }

        loadingRequest.dataRequest?.respondWithData(nsData)
        loadingRequest.finishLoading()
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    private fun handleCustomPlaylistRequest(loadingRequest: AVAssetResourceLoadingRequest) {
        val customUrl = loadingRequest.request.URL?.absoluteString ?: run {
            reportError(loadingRequest, badRequestErrorCode)
            return
        }

        customPlaylists[customUrl]?.let { playlist ->
            val masterData: ByteArray = playlist.encodeToByteArray()
            respondWithPlaylist(loadingRequest, masterData)
            return
        }

        val originalUrlString = toOriginalUrl(customUrl)
        val nsUrl = NSURL.URLWithString(originalUrlString) ?: run {
            reportError(loadingRequest, badRequestErrorCode)
            return
        }

        val request = NSURLRequest.requestWithURL(nsUrl)
        val session = NSURLSession.sharedSession
        val task = session.dataTaskWithRequest(request) { data, _, error ->
            if (error != null || data == null) {
                reportError(loadingRequest, badRequestErrorCode)
                return@dataTaskWithRequest
            }

            val mpdText = NSString.create(data, NSUTF8StringEncoding) ?: run {
                reportError(loadingRequest, badRequestErrorCode)
                return@dataTaskWithRequest
            }

            val mpdInfo = extractMpdInfo(mpdText as String)
            try {
                val mediaPlaylists = createMediaPlaylist(mpdInfo, customUrl)
                val masterPlaylist = createMasterPlaylist(mpdInfo, mediaPlaylists)

                val masterData: ByteArray = masterPlaylist.encodeToByteArray()

                val nsDataMaster: NSData = masterData.usePinned { pinned ->
                    NSData.create(
                        bytes = pinned.addressOf(0),
                        length = masterData.size.toULong()
                    )
                }
                loadingRequest.dataRequest?.respondWithData(nsDataMaster)
                loadingRequest.finishLoading()
            } catch (t: Throwable) {
                t.printStackTrace()
                reportError(loadingRequest, badRequestErrorCode)
            }
        }

        task.resume()
    }
}
