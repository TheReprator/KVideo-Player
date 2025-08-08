@file:OptIn(ExperimentalWasmJsInterop::class)

package dev.reprator.video.platform.impl

import dev.reprator.video.utils.VIDEO_JS_CSS_ID
import dev.reprator.video.utils.VIDEO_JS_CSS_URL
import dev.reprator.video.utils.VIDEO_JS_ID
import dev.reprator.video.utils.VIDEO_JS_URL
import dev.reprator.video.utils.isVideoJsFuncAvailable
import dev.reprator.video.utils.loadCss
import dev.reprator.video.utils.loadJsScript
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsException

class PlaybackStateControllerImpl(override val player: VideoPlayer): PlaybackStateController {

     val player1: VideoPlayer by lazy {

     }

    override suspend fun setupPlayer(): Boolean {
        try {
            loadJsScript(VIDEO_JS_URL, VIDEO_JS_ID)
            loadCss(VIDEO_JS_CSS_URL, VIDEO_JS_CSS_ID)
            if (isVideoJsFuncAvailable()) {
                println("1 PlaybackStateControllerImpl")
                return true
            }
        } catch (e: JsException) {
            println("1 PlaybackStateControllerImpl 1.1 error: ${e.message}")
            e.printStackTrace()
        } catch (e: Exception) {
            println("1 PlaybackStateControllerImpl 1.2 error: ${e.message}")
            e.printStackTrace()
        }
        return false
    }

    override fun getPlayerInstance(updatePlayer: (VideoPlayer) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        /*
            No need, as by default all NPM module are initialized in JS/WASM,
            lazy loading not supported for now natively
         */
    }


}