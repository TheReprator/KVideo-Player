package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoSource
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView

class VideoPlayerJvmImpl(private val mediaView: MediaView): VideoPlayer {

    private var mediaPlayer: MediaPlayer? = null

    private var isDisposed = false

    override fun play() {
       mediaPlayer?.play()
    }

    override fun pause() {
        mediaPlayer?.pause()
    }

    override fun isDisposed(): Boolean {
        return isDisposed
    }

    override fun dispose() {
        mediaPlayer?.dispose()
        isDisposed = true
    }

    override fun changeMedia(videoSource: VideoSource) {
        mediaPlayer?.dispose()
        mediaPlayer = MediaPlayer(Media(videoSource.src))
        mediaView.mediaPlayer = mediaPlayer
        mediaPlayer?.play()
    }
}