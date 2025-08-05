@file:OptIn(ExperimentalWasmJsInterop::class)

package external

@JsModule("video.js")
external fun videojs(playerId: String): VideoJSPlayer

@JsModule("video.js")
external fun videojs(id: JsAny, options: VideoJsOptions?, ready: VideoJSPlayer?.() -> Unit = definedExternally): VideoJSPlayer

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

external interface VideoSource: JsAny {
    var src: String
    var type: String?
}

external interface VideoJsOptions : JsAny {
    var controls: Boolean?
    var autoplay: Boolean?

    var loop: Boolean?

    var poster: String?
    var preload: String?

    var src: String?

    var autoPlay: Boolean?

    var muted: Boolean?

    var id: String?
    var sources: JsArray<VideoSource>?
}