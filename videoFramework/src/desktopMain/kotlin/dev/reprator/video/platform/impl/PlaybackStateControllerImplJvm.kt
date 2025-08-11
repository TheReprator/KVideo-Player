package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent

class PlaybackStateControllerImplJvm() : PlayerController {

    override lateinit var player: VideoPlayer

    val component: CallbackMediaPlayerComponent by lazy {
        CallbackMediaPlayerComponent()
    }

    override suspend fun setupPlayer(): Boolean {
        val isLibFound = NativeDiscovery().discover()
        if (!isLibFound)
            return false
        player = VideoPlayerJvmImpl(component.mediaPlayer())
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        component.mediaPlayer().submit {
            component.mediaPlayer().apply {
                media().play(initOptions.sources.first().src)
                /*audio().isMute = initOptions.muted
                if (initOptions.autoplay)
                    media().play(initOptions.sources.first().src, "-vvv")
                else
                    media().prepare(initOptions.sources.first().src, "-vvv")*/
            }
        }
    }

}