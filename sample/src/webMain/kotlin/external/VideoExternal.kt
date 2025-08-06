package external

import modals.VideoInitOptionsModal
import modals.VideoPlayer
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName

@JsModule("video.js")
@JsName("default")
external fun videojs(id: JsAny, options: VideoInitOptionsModal): VideoPlayer