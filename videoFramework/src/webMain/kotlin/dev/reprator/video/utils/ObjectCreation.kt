@file:OptIn(ExperimentalWasmJsInterop::class)

package dev.reprator.video.utils

import dev.reprator.video.modals.InternalVideoInitOptionsModal
import dev.reprator.video.modals.InternalVideoSource
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toJsArray

fun createVideoOptionsObject(videoSrcUrl: String, videoType: String): InternalVideoInitOptionsModal {

    val source = createVideoSource(videoSrcUrl, videoType)

    val options = newJsObject<InternalVideoInitOptionsModal>()
    options.controls = true
    options.autoplay = false // Defaulting to false as per your previous code
    options.preload = "auto"
    options.sources = listOf(source).toJsArray()
    return options
}


fun createVideoSource(videoSrcUrl: String, videoType: String): InternalVideoSource {
    val source = newJsObject<InternalVideoSource>()
    source.src = videoSrcUrl
    //source.type = videoType
    return source
}