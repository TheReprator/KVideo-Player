package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal
import dev.reprator.video.platform.impl.PlayerController
import dev.reprator.video.platform.impl.VideoPlayer

class PlaybackStateControllerImplIos() : PlayerController {

    override lateinit var player: VideoPlayer

    override suspend fun setupPlayer(): Boolean {
        player = VideoPlayerImplIos()
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        TODO()
    }

}