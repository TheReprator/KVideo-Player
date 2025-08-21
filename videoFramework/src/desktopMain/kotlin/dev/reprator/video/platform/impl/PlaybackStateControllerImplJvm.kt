package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal
import javafx.scene.media.MediaView

class PlaybackStateControllerImplJvm() : PlayerController {

    override lateinit var player: VideoPlayer

    val mediaView by lazy {
        MediaView().apply {
            isPreserveRatio = true
            isSmooth = true
        }
    }

    override suspend fun setupPlayer(): Boolean {
        player = VideoPlayerJvmImpl(mediaView)
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        player.changeMedia(initOptions.sources.first())
    }

}