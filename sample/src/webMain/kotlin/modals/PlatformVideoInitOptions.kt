package modals

import kotlin.js.JsAny
import kotlin.js.JsArray

external interface PlatformVideoInitOptions: JsAny  {
    var controls: Boolean?

    var autoplay: Boolean?

    var loop: Boolean?

    var poster: String?

    var preload: String?

    var src: String?

    var autoPlay: Boolean?

    var muted: Boolean?

    var id: String?

    var sources: JsArray<PlatformVideoSource>?
}