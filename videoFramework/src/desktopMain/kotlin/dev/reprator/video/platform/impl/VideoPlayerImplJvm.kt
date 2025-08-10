package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoSource
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer

class VideoPlayerJvmImpl(val mediaPlayer: EmbeddedMediaPlayer): VideoPlayer {

    override fun play() {
        mediaPlayer.controls().play()
    }

    override fun pause() {
        mediaPlayer.controls().pause()
    }

    override fun isDisposed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }

    override fun changeMedia(videoSource: VideoSource) {
        TODO("Not yet implemented")
    }

}