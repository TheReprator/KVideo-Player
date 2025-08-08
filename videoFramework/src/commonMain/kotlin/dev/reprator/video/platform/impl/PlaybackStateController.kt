package dev.reprator.video.platform.impl


interface PlaybackStateController {
    val player: VideoPlayer
    suspend fun setupPlayer(): Boolean
    fun getPlayerInstance(updatePlayer:(VideoPlayer)-> Unit)
    fun initPlayer(initOptions:VideoInitOptionModal)
}

interface VideoPlayer {
    fun play()
    fun pause()
    fun isDisposed(): Boolean
    fun dispose()
    fun chaneMedia(videoSource: VideoSource)
}


data class VideoInitOptionModal(
    val controls: Boolean = true,
    val autoplay: Boolean = false,
    val poster: String?,
    val preload: String = "auto",
    val autoPlay: Boolean = false,
    val muted: Boolean = false,
    val id: String?,
    val sources: List<VideoSource>)


data class VideoSource(
    val src: String,
    val type: String,
    val poster: String,
)
