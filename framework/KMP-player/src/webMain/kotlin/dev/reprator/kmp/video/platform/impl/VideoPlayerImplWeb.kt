package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.modals.InternalVideoPlayer
import dev.reprator.kmp.video.modals.VideoSource
import dev.reprator.kmp.video.utils.VIDEO_JS_CSS_ID
import dev.reprator.kmp.video.utils.VIDEO_JS_ID
import dev.reprator.kmp.video.utils.appGetElementById
import dev.reprator.kmp.video.utils.createVideoSource
import web.dom.document

class VideoPlayerImplWeb(private val webVideoPlayer: InternalVideoPlayer): VideoPlayer {

    override fun play() = webVideoPlayer.play()

    override fun pause() = webVideoPlayer.pause()

    override fun isDisposed(): Boolean = webVideoPlayer.isDisposed()

    override fun dispose() {
        webVideoPlayer.dispose()
        val videoCssModule = appGetElementById(VIDEO_JS_CSS_ID)
        if(null != videoCssModule)
            document.head.removeChild(videoCssModule)

        val videoJsModule = appGetElementById(VIDEO_JS_ID) ?: return
        document.head.removeChild(videoJsModule)
    }

    override fun changeMedia(videoSource: VideoSource) {
        val src = createVideoSource(
            videoSrcUrl = videoSource.src
        )
        webVideoPlayer.poster(videoSource.poster)
        webVideoPlayer.src(src)
    }
}