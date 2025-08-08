package dev.reprator.video.platform.impl

import dev.reprator.video.modals.InternalVideoPlayer
import dev.reprator.video.utils.createVideoSource

class VideoPlayerImpl(private val webVideoPlayer: InternalVideoPlayer): VideoPlayer {

    override fun play() {
        return webVideoPlayer.play()
    }

    override fun pause() {
        return webVideoPlayer.pause()
    }

    override fun isDisposed(): Boolean {
        return webVideoPlayer.isDisposed()
    }

    override fun dispose() {
        return webVideoPlayer.dispose()
    }

    override fun chaneMedia(videoSource: VideoSource) {
        val src = createVideoSource(
            videoSrcUrl = videoSource.src,
            videoType = videoSource.type
        )
        webVideoPlayer.poster(videoSource.poster)
        webVideoPlayer.src(src)
    }
}