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
        return false
    }

    override fun dispose() {
        mediaPlayer.release()
    }

    override fun changeMedia(videoSource: VideoSource) {
        mediaPlayer.submit {
            mediaPlayer.media().play(videoSource.src)
        }
    }

}