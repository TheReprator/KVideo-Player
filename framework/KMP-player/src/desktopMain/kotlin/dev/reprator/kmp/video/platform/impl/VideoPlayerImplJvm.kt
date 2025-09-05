package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.modals.VideoSource
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer

class VideoPlayerJvmImpl(private val mediaPlayer: EmbeddedMediaPlayer): VideoPlayer {

    private var isDisposed = false

    override fun play() {
        mediaPlayer.controls().play()
    }

    override fun pause() {
        mediaPlayer.status()
        mediaPlayer.controls().pause()
    }

    override fun isDisposed(): Boolean {
        return isDisposed
    }

    override fun dispose() {
        mediaPlayer.release()
        isDisposed = true
    }

    override fun changeMedia(videoSource: VideoSource) {
        mediaPlayer.submit {
            mediaPlayer.media().play(videoSource.src)
        }
    }

}