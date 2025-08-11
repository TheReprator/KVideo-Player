@file:OptIn(ExperimentalWasmJsInterop::class)

package dev.reprator.video.platform.impl

import androidx.compose.runtime.mutableStateOf
import dev.reprator.video.platform.external.videojs
import dev.reprator.video.modals.VideoInitOptionModal
import dev.reprator.video.utils.VIDEO_JS_CSS_ID
import dev.reprator.video.utils.VIDEO_JS_CSS_URL
import dev.reprator.video.utils.VIDEO_JS_ID
import dev.reprator.video.utils.VIDEO_JS_URL
import dev.reprator.video.utils.createVideoOptionsObject
import dev.reprator.video.utils.isVideoJsFuncAvailable
import dev.reprator.video.utils.loadCss
import dev.reprator.video.utils.loadJsScript
import web.cssom.ClassName
import web.dom.ElementId
import web.dom.document
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsException
import kotlin.random.Random

class PlaybackStateControllerImplWeb : PlayerController {

    override lateinit var player: VideoPlayer

    val videoElement by lazy {
        val videoElement = document.createElement("video-js")
        videoElement.id = ElementId("dynamic-video-js-${Random.nextInt()}")
        videoElement.className = ClassName("vjs-big-play-centered")

        mutableStateOf(videoElement)
    }

    override suspend fun setupPlayer(): Boolean {
        try {
            loadJsScript(VIDEO_JS_URL, VIDEO_JS_ID)
            loadCss(VIDEO_JS_CSS_URL, VIDEO_JS_CSS_ID)
            return isVideoJsFuncAvailable()
        } catch (e: JsException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        val videoInitObject = createVideoOptionsObject(initOptions)
        val webPlayer = videojs(videoElement.value, videoInitObject)

        player = VideoPlayerImplWeb(webPlayer)
    }
}