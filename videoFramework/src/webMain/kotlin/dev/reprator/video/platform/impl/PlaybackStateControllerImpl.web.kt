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
import web.events.Event
import web.events.OFFLINE
import web.events.ONLINE
import web.events.addEventListener
import web.events.removeEventListener
import web.window.window
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsException
import kotlin.random.Random

private val TRACKING_WINDOW_LIST = listOf(Event.OFFLINE, Event.ONLINE)

class PlaybackStateControllerImpl() : PlayerController {

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
        val videoSource = initOptions.sources.first()
        val videoInitObject = createVideoOptionsObject(videoSource.src, videoSource.type)
        val newVidePlayer = videojs(videoElement.value, videoInitObject)

        player = VideoPlayerImpl(newVidePlayer)
    }

    override fun startEventTracking() {
        TRACKING_WINDOW_LIST.forEach {
            window.addEventListener(it, {
                println("${it.type} callback added")
            })
        }
    }

    override fun stopEventTracking() {
        TRACKING_WINDOW_LIST.forEach {
            window.removeEventListener(it, {
            })
        }
    }
}