@file:JsModule("video.js")
@file:JsNonModule

package external

import kotlinx.js.JsPlainObject
import kotlin.js.*
import org.w3c.dom.*


@JsName("default")
external fun videojs(id: Element, options: VideoJsOptions? = definedExternally, ready: (Player?) = definedExternally): Player

@JsName("default")
external fun videojs(id: Element): Player

@JsName("default")
external fun videojs(id: Element, options: VideoJsOptions): Player

@JsPlainObject
external interface VideoJsOptions  {
    val controls: Boolean
    val autoplay: Boolean

    val preload: String?
    val sources: Array<VideoSource>?
}

@JsPlainObject
external interface VideoSource {
    val src: String
    val type: String?
}
