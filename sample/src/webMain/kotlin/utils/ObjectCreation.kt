package utils

import modals.VideoInitOptionsModal
import modals.VideoSource
import kotlin.js.toJsArray

fun createVideoOptionsObject(videoSrcUrl: String, videoType: String): VideoInitOptionsModal {

    val source = createVideoSource(videoSrcUrl, videoType)

    val options = newJsObject<VideoInitOptionsModal>()
    options.controls = true
    options.autoplay = false // Defaulting to false as per your previous code
    options.preload = "auto"
    options.sources = listOf(source).toJsArray()
    return options
}


fun createVideoSource(videoSrcUrl: String, videoType: String): VideoSource {
    val source = newJsObject<VideoSource>()
    source.src = videoSrcUrl
    //source.type = videoType
    return source
}