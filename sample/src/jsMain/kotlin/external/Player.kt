@file:JsModule("video.js")
@file:JsNonModule

package external

import kotlin.js.*

@JsName("default")
open external class Player() {
    fun dispose()
    fun play()
    fun pause()
    fun src(sources: VideoSource?): String?
    fun load()
    fun poster(src: String?): String?
    fun isDisposed(): Boolean?
    fun on(eventName: String, callback: (Any) -> Unit)
    fun off(eventName: String)
    fun off()
}