@file:OptIn(ExperimentalWasmJsInterop::class)

package external

import modals.PlatformVideoInitOptions
import modals.PlatformVideoSource

@JsModule("video.js")
external fun videojs(playerId: String): VideoJSPlayer

@JsModule("video.js")
external fun videojs(id: JsAny, options: VideoInitOptions?, ready: VideoJSPlayer?.() -> Unit = definedExternally): VideoJSPlayer

external interface VideoJSPlayer: JsAny {
    fun dispose()
    fun play()
    fun pause()
    fun src(sources: VideoSource?): String?
    fun load()
    fun poster(src: String?): String?
    fun isDisposed(): Boolean?
    fun on(eventName: String, callback: JsAny?.() -> Unit)
    fun off(eventName: String)
    fun off()
}

external interface VideoSource: PlatformVideoSource

external interface VideoInitOptions : PlatformVideoInitOptions