package dev.reprator.video.modals

import kotlin.js.JsAny

external interface VideoSource: JsAny {
    var src: String
    var type: String?
}