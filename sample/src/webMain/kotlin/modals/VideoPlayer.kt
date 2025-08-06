package modals

import kotlin.js.JsAny

external interface VideoPlayer  {
    fun dispose()
    fun play()
    fun pause()
    fun src(sources: VideoSource?): String?
    fun load()
    fun poster(src: String?): String?
    fun isDisposed(): Boolean?
    fun on(eventName: String, callback: (JsAny) -> Unit)
    fun off(eventName: String)
    fun off()
}