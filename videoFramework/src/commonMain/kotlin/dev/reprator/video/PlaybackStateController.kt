package dev.reprator.video


interface PlaybackStateController {
    fun initPlayer(initOptions:VideoInitOptionModal)
    fun pause()
    fun play()
    fun isDisposed(): Boolean
    fun dispose()
    fun isPlaying(): Boolean
    fun chaneMedia(items: List<VideoSource>)
}


data class VideoInitOptionModal(
    val controls: Boolean,
    val autoplay: Boolean,
    val poster: String,
    val preload: String,
    val autoPlay: Boolean,
    val muted: Boolean,
    val id: String,
    val sources: List<VideoSource>)


data class VideoSource(
    val src: String,
    val type: String
)
