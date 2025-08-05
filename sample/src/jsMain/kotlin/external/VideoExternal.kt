
package external

@JsModule("video.js")
@JsNonModule
external fun videojs(playerId: String): VideoJSPlayer

@JsModule("video.js")
@JsNonModule
external fun videojs(id: Any, options: VideoJsOptions?, ready: Any = definedExternally): VideoJSPlayer

external interface VideoJSPlayer {
    fun dispose()
    fun play()
    fun pause()
    fun src(sources: VideoSource?): String?
    fun load()
    fun poster(src: String?): String?
    fun isDisposed(): Boolean?
    fun on(eventName: String, callback: Any = definedExternally)
    fun off(eventName: String)
    fun off()
}

external interface VideoSource {
    var src: String
    var type: String?
}

external interface VideoJsOptions {
    var controls: Boolean?
    var autoplay: Boolean?

    var loop: Boolean?

    var poster: String?
    var preload: String?

    var src: String?

    var autoPlay: Boolean?

    var muted: Boolean?

    var id: String?
    var sources: Array<VideoSource>?
}