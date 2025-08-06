package modals

import kotlin.js.JsAny

external interface PlatformVideoSource: JsAny {
    var src: String
    var type: String?
}