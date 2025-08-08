@file:OptIn(ExperimentalWasmJsInterop::class)
package dev.reprator.video.external

import dev.reprator.video.modals.InternalVideoInitOptionsModal
import dev.reprator.video.modals.InternalVideoPlayer
import web.dom.Element
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule
import kotlin.js.JsName

@JsModule("video.js")
@JsName("default")
external fun videojs(id: Element, options: InternalVideoInitOptionsModal): InternalVideoPlayer