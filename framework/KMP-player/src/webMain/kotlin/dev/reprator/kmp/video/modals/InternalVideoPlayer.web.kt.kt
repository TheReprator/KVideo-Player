@file:OptIn(ExperimentalWasmJsInterop::class)
package dev.reprator.kmp.video.modals

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny

external interface InternalVideoPlayer  {
    fun dispose()
    fun play()
    fun pause()
    fun src(sources: InternalVideoSource?): String?
    fun load()
    fun poster(src: String?): String?
    fun isDisposed(): Boolean
    fun on(eventName: String, callback: (JsAny) -> Unit)
    fun off(eventName: String)
    fun off()
}