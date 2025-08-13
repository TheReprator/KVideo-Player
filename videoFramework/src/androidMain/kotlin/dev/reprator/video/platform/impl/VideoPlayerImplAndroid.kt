package dev.reprator.video.platform.impl

import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import dev.reprator.video.modals.VideoSource

class VideoPlayerImplAndroid(private val mediaPlayer: ExoPlayer): VideoPlayer {


    override fun play() {
        mediaPlayer.play()
    }

    override fun pause() {
        mediaPlayer.pause()
    }

    @OptIn(UnstableApi::class)
    override fun isDisposed(): Boolean {
        return mediaPlayer.isReleased
    }

    override fun dispose() {
        mediaPlayer.release()
    }

    override fun changeMedia(videoSource: VideoSource) {
        mediaPlayer.setMediaItem(MediaItem.fromUri(videoSource.src))
        mediaPlayer.prepare()
        mediaPlayer.play()
    }

}