@file:OptIn(ExperimentalWasmJsInterop::class)

package dev.reprator.video.utils

import dev.reprator.video.modals.InternalVideoInitOptionsModal
import dev.reprator.video.modals.InternalVideoSource
import dev.reprator.video.modals.VideoInitOptionModal
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toJsArray

fun createVideoOptionsObject(initOptions: VideoInitOptionModal): InternalVideoInitOptionsModal {

    val sourceList = initOptions.sources.map {
        createVideoSource(it.src, it.type)
    }

    val options = newJsObject<InternalVideoInitOptionsModal>()
    options.controls = initOptions.controls
    options.autoplay = initOptions.autoplay
    options.preload = initOptions.preload
    options.poster = initOptions.sources.firstOrNull()?.poster
    options.sources = sourceList.toJsArray()
    return options
}

fun createVideoSource(videoSrcUrl: String, videoType: String): InternalVideoSource {
    val source = newJsObject<InternalVideoSource>()
    source.src = videoSrcUrl
    //source.type = videoType
    return source
}