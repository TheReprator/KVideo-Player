package dev.reprator.video.external

import dev.reprator.video.modals.VideoInitOptionsModal
import dev.reprator.video.modals.VideoPlayer
import web.dom.Element
import kotlin.js.JsModule
import kotlin.js.JsName

@JsModule("video.js")
@JsName("default")
external fun videojs(id: Element, options: VideoInitOptionsModal): VideoPlayer