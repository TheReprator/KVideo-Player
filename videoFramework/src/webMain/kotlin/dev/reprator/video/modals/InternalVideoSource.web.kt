@file:OptIn(ExperimentalWasmJsInterop::class)
package dev.reprator.video.modals

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny

external interface InternalVideoSource: JsAny {
    var src: String
    var type: String?
}