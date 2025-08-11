package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent

class PlaybackStateControllerImplJvm() : PlayerController {

    override lateinit var player: VideoPlayer
    lateinit var component: CallbackMediaPlayerComponent

    override suspend fun setupPlayer(): Boolean {
        print("Vikram: Singh:: check0, startTime: ${System.currentTimeMillis()}")
        val isLibFound = NativeDiscovery().discover()
        if (!isLibFound)
            return false
        print("Vikram: Singh:: check1, endTime: ${System.currentTimeMillis()}")
        component = CallbackMediaPlayerComponent()
        player = VideoPlayerJvmImpl(component.mediaPlayer())
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        print("Vikram: Singh:: check2")
        component.mediaPlayer().media().play(initOptions.sources.first().src)
    }

}