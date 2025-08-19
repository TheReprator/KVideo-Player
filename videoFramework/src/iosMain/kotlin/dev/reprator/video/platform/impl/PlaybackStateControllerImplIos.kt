package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal
import dev.reprator.video.platform.extra.CustomPlaylistDelegate
import dev.reprator.video.platform.extra.toCustomUrl
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.AVFoundation.AVKeyValueStatusFailed
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.play
import platform.AVFoundation.playable
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVFoundation.resourceLoader
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSBundle
import platform.Foundation.NSError
import platform.Foundation.NSLocalizedDescriptionKey
import platform.Foundation.NSLocalizedFailureReasonErrorKey
import platform.Foundation.NSURL
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_create

class PlaybackStateControllerImplIos() : PlayerController {
    override lateinit var player: VideoPlayer

    val playerController: AVPlayerViewController by lazy {
        val controller = AVPlayerViewController()
        controller.player = AVPlayer()
        controller
    }

    override suspend fun setupPlayer(): Boolean {
        player = VideoPlayerImplIos(playerController)
        return true
    }

    private val resourceLoaderDelegate: CustomPlaylistDelegate by lazy {
        CustomPlaylistDelegate()
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        val videoSource = initOptions.sources.first()
        val url = videoSource.src
        if (url.endsWith(".mpd")) {
            tp(toCustomUrl(url))
        } else {
            val mediaItem = AVPlayerItem(NSURL.URLWithString(url)!!)
            playerController.showsPlaybackControls = true
            playerController.player!!.replaceCurrentItemWithPlayerItem(mediaItem)
            playerController.player!!.play()
        }
    }


    private fun tp(url: String){
        val newValue = NSURL.URLWithString(url)!!

        val asset = AVURLAsset(uRL = newValue, options = null)
        asset.resourceLoader.setDelegate(resourceLoaderDelegate, queue = dispatch_queue_create("AVARLDelegateDemo loader", null))


        val requestedKeys = listOf("playable", "duration")

        asset.loadValuesAsynchronouslyForKeys(requestedKeys) {
            dispatch_async(dispatch_get_main_queue()) {
                this.prepareToPlay(asset, requestedKeys)
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    private fun prepareToPlay(asset: AVURLAsset, requestedKeys: List<String>) {
        requestedKeys.forEach { key ->
            memScoped {
                val errorPtr = alloc<ObjCObjectVar<NSError?>>()
                val status = asset.statusOfValueForKey(key, error = errorPtr.ptr)
                if (status == AVKeyValueStatusFailed) {
                    val error: NSError? = errorPtr.value
                    println(error?.localizedDescription ?: "")
                    println(error?.localizedFailureReason ?: "")
                    return
                }
            }
        }

        if (!asset.playable) {
            val error = NSError.errorWithDomain(
                domain = NSBundle.mainBundle.bundleIdentifier ?: "",
                code = 0,
                userInfo = mapOf(
                    NSLocalizedDescriptionKey to "Item cannot be played",
                    NSLocalizedFailureReasonErrorKey to "The contents of the resource at the specified URL are not playable."
                )
            )
            println(error.localizedDescription)
            println(error.localizedFailureReason)
            return
        }

        val mediaItem = AVPlayerItem(asset)
        playerController.player!!.replaceCurrentItemWithPlayerItem(mediaItem)
        playerController.player!!.play()
    }
}