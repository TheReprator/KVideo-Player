package dev.reprator.kmp.video.platform.dashHandler

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.AVFoundation.AVKeyValueStatusFailed
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.playable
import platform.AVFoundation.resourceLoader
import platform.Foundation.NSBundle
import platform.Foundation.NSError
import platform.Foundation.NSLocalizedDescriptionKey
import platform.Foundation.NSLocalizedFailureReasonErrorKey
import platform.Foundation.NSURL
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_create


interface DashHandler {
    fun playDashFile(url: String, play: AVPlayerItem.() -> Unit) {
    }
}

class DashHandlerImpl : DashHandler {

    val requestedKeys = listOf("playable", "duration")

    private val resourceLoaderDelegate: CustomPlaylistDelegate by lazy {
        CustomPlaylistDelegate()
    }

    override fun playDashFile(url: String, play: AVPlayerItem.() -> Unit) {
        val newValue = NSURL.Companion.URLWithString(toCustomUrl(url))!!

        val asset = AVURLAsset(uRL = newValue, options = null)
        asset.resourceLoader.setDelegate(
            resourceLoaderDelegate, queue = dispatch_queue_create(
                "AVARLDelegateDemo loader",
                null
            )
        )

        asset.loadValuesAsynchronouslyForKeys(requestedKeys) {
            dispatch_async(dispatch_get_main_queue()) {
                this.prepareToPlay(asset, requestedKeys, play)
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    private fun prepareToPlay(
        asset: AVURLAsset,
        requestedKeys: List<String>,
        play: AVPlayerItem.() -> Unit
    ) {
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
            val error = NSError.Companion.errorWithDomain(
                domain = NSBundle.Companion.mainBundle.bundleIdentifier ?: "",
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
        play(mediaItem)
    }
}
