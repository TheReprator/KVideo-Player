@file:Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY", "FunctionName")

package dev.reprator.video.platform.extra

import dev.reprator.video.platform.extra.models.AdaptationSetAudio
import dev.reprator.video.platform.extra.models.AdaptationSetVideo
import dev.reprator.video.platform.extra.models.Mapper
import dev.reprator.video.platform.extra.models.MpdInfo
import dev.reprator.video.platform.extra.models.RawResponseMpdInfo
import kotlinx.cinterop.*
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.XML
import platform.AVFoundation.*
import platform.Foundation.*
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.math.ceil

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
        println("Resource loader requested: $customUrl")
        if (isCustomPlaylistSchemeValid(scheme)) {
            // Run asynchronously on main thread
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

    @Throws(ParseError::class)
    fun createMediaPlaylist(mpdInfo: MpdInfo, url: String): List<String> {
        val header = "#EXTM3U\n#EXT-X-VERSION:6\n"
        val tail = "#EXT-X-ENDLIST"

        val mediaPlaylists = mutableListOf<String>()
        val baseUrl = url.substringBeforeLast("/")
            .replace(customPlaylistScheme, httpsScheme)

        var j = 0
        for (adaptationSet in mpdInfo.adaptationSets) {
            when (adaptationSet) {
                is AdaptationSetVideo -> {
                    adaptationSet.representation.forEach { rep ->
                        val playlistType = "#EXT-X-PLAYLIST-TYPE:EVENT\n"

                        val firstSequence = rep.segmentTemplate.startNumber
                        val startSequence = "#EXT-X-MEDIA-SEQUENCE:$firstSequence\n"

                        val totalDuration =
                            rep.segmentTemplate.duration.toDouble() / rep.segmentTemplate.timescale.toDouble()

                        val segmentsName = rep.segmentTemplate.media
                        val segmentDuration = totalDuration
                        val numSegments = ceil(totalDuration / segmentDuration).toInt()

                        var segmentUnit = ""
                        var maxDuration = 1

                        for (i in firstSequence..numSegments) {
                            val dur = if (i == numSegments) {
                                totalDuration - segmentDuration * (numSegments - 1)
                            } else segmentDuration
                            val rounded = dur.toInt()
                            if (maxDuration < rounded) maxDuration = rounded

                            val inf = "#EXTINF:$dur\n"
                            val segmentUrl = baseUrl + "/" +
                                    segmentsName.replace(Regex("\\$.*?\\$"), i.toString()) + "\n"
                            segmentUnit += inf + segmentUrl
                        }

                        val videoInfo = buildString {
                            append("#EXT-X-MLB-VIDEO-INFO:")
                            append("""codecs="${rep.codecs}",""")
                            append("""width="${rep.width}",""")
                            append("""height="${rep.height}",""")
                            append("""frame-rate="${rep.frameRate}"""")
                            append("\n")
                        }

                        val mapInit = """#EXT-X-MAP:URI="$baseUrl/${rep.segmentTemplate.initialization}"""" + "\n"
                        val info = """#EXT-X-MLB-INFO:max-bw=${rep.bandwidth},duration=$totalDuration""" + "\n"

                        val playlist = buildString {
                            append("#EXT-X-TARGETDURATION:$maxDuration\n")
                            append(startSequence)
                            append(playlistType)
                            append(mapInit)
                            append(segmentUnit)
                            append(videoInfo)
                            append(info)
                        }

                        mediaPlaylists.add(header + playlist + tail)
                        j++
                    }
                }

                is AdaptationSetAudio -> {
                    adaptationSet.representation.forEach { rep ->
                        val totalDuration =
                            rep.segmentTemplate.duration.toDouble() / rep.segmentTemplate.timescale.toDouble()
                        val firstSequence = rep.segmentTemplate.startNumber
                        val startSequence = "#EXT-X-MEDIA-SEQUENCE:$firstSequence\n"

                        val segmentsName = rep.segmentTemplate.media
                        val segmentDuration =
                            rep.segmentTemplate.duration.toDouble() / rep.segmentTemplate.timescale.toDouble()
                        val numSegments = kotlin.math.ceil(totalDuration / segmentDuration).toInt()

                        var segmentUnit = ""
                        var maxDuration = 1

                        for (i in firstSequence..numSegments) {
                            val dur = if (i == numSegments) {
                                totalDuration - segmentDuration * (numSegments - 1)
                            } else segmentDuration
                            val rounded = dur.toInt()
                            if (maxDuration < rounded) maxDuration = rounded

                            val inf = "#EXTINF:$dur\n"
                            val segmentUrl = baseUrl + "/" +
                                    segmentsName.replace(Regex("\\$.*?\\$"), i.toString()) + "\n"
                            segmentUnit += inf + segmentUrl
                        }

                        val audioInfo = """#EXT-X-MLB-AUDIO-INFO:codecs="${rep.codecs}",audioSamplingRate="${rep.audioSamplingRate}"""" + "\n"
                        val channelInfo = """#EXT-X-MLB-AUDIO-CHANNEL-INFO:schemeIdUri="${rep.audioChannelConfiguration.schemeIdUri}",value="${rep.audioChannelConfiguration.value}"""" + "\n"

                        val mapInit = """#EXT-X-MAP:URI="$baseUrl/${rep.segmentTemplate.initialization}"""" + "\n"
                        val info = """#EXT-X-MLB-INFO:max-bw=${rep.bandwidth},duration=$totalDuration""" + "\n"

                        val playlist = buildString {
                            append("#EXT-X-TARGETDURATION:$maxDuration\n")
                            append(startSequence)
                            append(mapInit)
                            append(segmentUnit)
                            append(audioInfo)
                            append(channelInfo)
                            append(info)
                        }

                        mediaPlaylists.add(header + playlist + tail)
                        j++
                    }
                }
            }
        }

        return mediaPlaylists
    }

    /* private fun createMediaPlaylist(mpdInfo: MpdInfo, url: String): List<String> {
         val header = "#EXTM3U\n#EXT-X-VERSION:6\n"
         val tail = "#EXT-X-ENDLIST"
         val mediaPlaylists = mutableListOf<String>()

         val index = url.lastIndexOf("/")
         if (index == -1) throw IllegalArgumentException("Invalid URL")

         val httpUrlPath = url.substring(0, index).replace(customPlaylistScheme, httpsScheme)

         var playlistIndex = 0

         for (adaptationSet in mpdInfo.adaptationSets) {
             val mimeType = adaptationSet.mimeType

             val timescale = segmentTemplate.timescale
             val rawDuration = segmentTemplate.duration
             val segmentDuration = rawDuration / timescale
             val startNumber = segmentTemplate.startNumber


             for (representation in adaptationSet.representation) {
                 val representation = when(representation) {
                     is AudioRepresentation -> {
                         "#EXT-X-MLB-AUDIO-INFO:" +
                                 "codecs=\"${representation.codecs}\"," +
                                 "audioSamplingRate=\"${representation.audioSamplingRate}\"\n"
                     }

                     is VideoRepresentation -> {
                         "#EXT-X-MLB-VIDEO-INFO:" +
                                 "codecs=\"${representation.codecs}\"," +
                                 "width=\"${representation.width}\"," +
                                 "height=\"${representation.height}\"\n"+
                                 "frame-duration=\"${representation.segmentTemplate.timescale}\"\n"
                     }
                 }
                 val totalDuration = getDuration(rawDuration, timescale)
                 val numSegment = kotlin.math.ceil(totalDuration / segmentDuration).toInt()

                 val startSequence = "#EXT-X-MEDIA-SEQUENCE:$startNumber\n"
                 val playlistType = "#EXT-X-PLAYLIST-TYPE:EVENT\n" // could check VOD vs live later
                 val mapInit = "#EXT-X-MAP:URI=\"" +
                         "$httpUrlPath/${segmentTemplate.initialization}\"" + "\n"

                 val info = "#EXT-X-MLB-INFO:max-bw=${representation.bandwidth},duration=$totalDuration\n"

                 var segmentUnit = ""
                 var maxDuration = 1

                 for (i in startNumber..numSegment) {
                     val segDur = if (i == numSegment) {
                         totalDuration - segmentDuration * (numSegment - 1)
                     } else {
                         segmentDuration
                     }
                     val duration = segDur.toDouble().roundToInt()

                     if (maxDuration < duration) maxDuration = duration

                     val inf = "#EXTINF:$segDur\n"
                     val segment = "$httpUrlPath/" +
                             segmentTemplate.media.replace(Regex("\\$.*?\\$"), i.toString()) + "\n"
                     segmentUnit += inf + segment
                 }

                 val maxSegmentDuration = "#EXT-X-TARGETDURATION:$maxDuration\n"

                 val extraInfo = when {
                     mimeType.contains("video") -> {
                         "#EXT-X-MLB-VIDEO-INFO:" +
                                 "codecs=\"${representation.codecs}\"," +
                                 "width=\"${representation.width}\"," +
                                 "height=\"${representation.height}\"\n"+
                                 "frame-duration=\"${segmentTemplate.timescale}\"\n"
                     }
                     mimeType.contains("audio") -> {
                         "#EXT-X-MLB-AUDIO-INFO:" +
                                 "codecs=\"${representation.codecs}\"," +
                                 "audioSamplingRate=\"${representation.audioSamplingRate}\"\n"+

                                 "#EXT-X-MLB-AUDIO-CHANNEL-INFO:" +
                                 "schemeIdUri=\"${representation.codecs}\"," +
                                 "audioSamplingRate=\"${representation.audioSamplingRate}\"\n"
                     }
                     else -> null
                 }

                 if (null != extraInfo) {
                     val output = header +
                             maxSegmentDuration +
                             startSequence +
                             playlistType +
                             mapInit +
                             segmentUnit +
                             extraInfo +
                             info +
                             tail

                     mediaPlaylists += saveAsCustomPlaylist(url, playlistIndex, output)
                     playlistIndex++
                 }
             }
         }

         return mediaPlaylists
     }*/

    fun createMasterPlaylist(mpdInfo: MpdInfo, mediaPlaylists: List<String>): String {
        val header = "#EXTM3U\n#EXT-X-VERSION:6\n"
        val output = StringBuilder()

        var j = 0

        mpdInfo.adaptationSets.forEach { adaptationSet ->
            when (adaptationSet) {
                is AdaptationSetVideo -> {
                    adaptationSet.representation.forEach { rep ->
                        // Trick play filter (skip frameRate == "1")
                        if (rep.frameRate == "1") {
                            j++
                            return@forEach
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
                        j++
                        output.appendLine(videoMasterInfo)
                        output.appendLine(videoMediaPlaylist)
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
                        j++
                        // prepend audio before video output
                        output.insert(0, audioMaster + "\n")
                    }
                }
            }
        }

        return header + output.toString()
    }

    /*private fun createMasterPlaylist(
        mediaPlaylists: List<String>,
        mpdInfo: MpdInfo
    ): String {
        val header = "#EXTM3U\n" +
                "#EXT-X-VERSION:6\n" +
                "#EXT-X-INDEPENDENT-SEGMENTS\n"

        val output = StringBuilder()

        for ((i, adaptationSet) in mpdInfo.adaptationSets.withIndex()) {
            for ((j, representation) in adaptationSet.representations.withIndex()) {

                // Build URI for the media playlist
                val rawMedia = mediaPlaylists[j]
                val videoMediaPlaylist =
                    if (rawMedia.contains("/")) rawMedia.substringAfterLast("/") else rawMedia

                // Build EXT-X-STREAM-INF line
                output.append(
                    buildString {
                        append("#EXT-X-STREAM-INF:")
                        append("BANDWIDTH=${representation.bandwidth},")
                        append("CODECS=\"${representation.codecs}\",")
                        append("RESOLUTION=${representation.width}x${representation.height}")
                        representation.frameRate?.let { append(",FRAME-RATE=$it") }
                        append("\n$videoMediaPlaylist\n")
                    }
                )

                // 🔍 Debug logs per representation
                println("🎬 Representation $i-$j")
                println("  bandwidth = ${representation.bandwidth}")
                println("  codecs    = ${representation.codecs}")
                println("  resolution= ${representation.width}x${representation.height}")
                println("  frameRate = ${representation.frameRate}")
                println("  playlist  = $videoMediaPlaylist")
            }
        }

        val playlist = header + output.toString()
        println("📜 Generated Master Playlist:\n$playlist")
        return playlist
    }*/

    @Throws(ParseError::class)
    private fun saveAsCustomPlaylist(url: String, index: Int, playlist: String): String {
        val i = url.lastIndexOf(".")
        if (i < 0) throw ParseError("invalidUrl")
        val playlistUrl = url.substring(0, i) + "_$index.$hlsExt"
        customPlaylists[playlistUrl] = playlist
        return playlistUrl
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    private fun respondWithPlaylist(loadingRequest: AVAssetResourceLoadingRequest, playlistBytes: ByteArray) {
        val cir = loadingRequest.contentInformationRequest
        cir?.let {
            // HLS master/media playlist MIME
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
            // data -> String
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
