@file:JsModule("video.js")
@file:JsNonModule

package external

import modals.PlatformVideoInitOptions
import modals.PlatformVideoSource
import kotlin.js.*
import org.w3c.dom.*


@JsName("default")
external fun videojs(id: Element, options: VideoInitOptions? = definedExternally, ready: (Player?) = definedExternally): Player

@JsName("default")
external fun videojs(id: Element): Player

@JsName("default")
external fun videojs(id: Element, options: VideoInitOptions): Player


external interface VideoInitOptions: PlatformVideoInitOptions

external interface VideoSource: PlatformVideoSource
