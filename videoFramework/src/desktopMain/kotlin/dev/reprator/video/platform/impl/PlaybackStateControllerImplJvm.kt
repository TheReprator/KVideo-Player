package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent

class PlaybackStateControllerImplJvm() : PlayerController {

    override lateinit var player: VideoPlayer
    lateinit var component: CallbackMediaPlayerComponent

    override suspend fun setupPlayer(): Boolean {
        NativeDiscovery().discover()
        component = CallbackMediaPlayerComponent()
        player = VideoPlayerJvmImpl(component.mediaPlayer())
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        //component.mediaPlayer().videoSurface().
        /*
        *  val controls: Boolean = true,
    val autoplay: Boolean = false,
    val poster: String?,
    val preload: String = "auto",
    val autoPlay: Boolean = false,
    val muted: Boolean = false,
    val id: String?,
    val sources: List<VideoSource>)
        * */
        TODO("Not yet implemented")
    }

}