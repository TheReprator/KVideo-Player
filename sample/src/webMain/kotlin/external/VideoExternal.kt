package external

import modals.VideoInitOptionsModal
import modals.VideoPlayer
import web.dom.Element
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName

@JsModule("video.js")
@JsName("default")
external fun videojs(id: Element, options: VideoInitOptionsModal): VideoPlayer