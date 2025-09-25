package dev.reprator.kmp.video.dashHandler

import dev.reprator.kmp.video.dashHandler.AVAssetResourceHandler.Companion.toCustomUrl
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.UnsafeNumber
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.AVFoundation.AVKeyValueStatusFailed
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.playable
import platform.Foundation.NSBundle
import platform.Foundation.NSError
import platform.Foundation.NSLocalizedDescriptionKey
import platform.Foundation.NSLocalizedFailureReasonErrorKey
import platform.Foundation.NSURL
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

interface DashHandler {
    fun playDashFile(url: String, play: AVPlayerItem.() -> Unit)

    companion object {
        fun getDashHandler(assetResourceHandler: AVAssetResourceHandler? = null): DashHandler {
            return DashHandlerImpl(assetResourceHandler)
        }
    }
}

class DashHandlerImpl(private val assetResourceHandler: AVAssetResourceHandler?) : DashHandler {

    val requestedKeys = listOf("playable", "duration")

    override fun playDashFile(url: String, play: AVPlayerItem.() -> Unit) {
        val newValue = NSURL.URLWithString(toCustomUrl(url))!!

        val asset = AVURLAsset(uRL = newValue, options = null)
        assetResourceHandler?.saveOffline(asset)

        asset.loadValuesAsynchronouslyForKeys(requestedKeys) {
            dispatch_async(dispatch_get_main_queue()) {
                this.prepareToPlay(asset, requestedKeys, play)
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class, UnsafeNumber::class)
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
        play(mediaItem)
    }
}
