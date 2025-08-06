package utils
import kotlin.js.JsAny
import kotlin.js.js


fun isVideoJsFuncAvailable(): Boolean = js("typeof videojs === 'function'")

fun <T : JsAny> newJsObject(): T = js("({})")