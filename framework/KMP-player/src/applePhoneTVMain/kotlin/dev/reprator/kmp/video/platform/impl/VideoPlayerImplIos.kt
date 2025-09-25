package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.modals.VideoSource
import dev.reprator.kmp.video.dashHandler.DashHandler
import dev.reprator.kmp.video.platform.assetHandler.AVAssetResourceHandlerImpl
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

class VideoPlayerImplIos(private val playerController: AVPlayerViewController) : VideoPlayer {

    private var isDisposed = false

    private val dashHandler: DashHandler by lazy {
        DashHandler.getDashHandler()
    }

    override fun play() {
        playerController.player!!.play()
    }

    override fun pause() {
        playerController.player!!.pause()
    }

    override fun isDisposed(): Boolean {
        return isDisposed
    }

    override fun dispose() {
        playerController.player!!.pause()
        playerController.player!!.replaceCurrentItemWithPlayerItem(null)
        playerController.player = null

        isDisposed = true
    }

    override fun changeMedia(videoSource: VideoSource) {
        if (videoSource.src.endsWith(".mpd")) {
            dashHandler.playDashFile(videoSource.src) {
                playerController.showsPlaybackControls = true
                playerController.player!!.replaceCurrentItemWithPlayerItem(this)
                playerController.player!!.play()
            }
        } else {
            val mediaItem = AVPlayerItem(NSURL.URLWithString(videoSource.src)!!)
            playerController.player!!.replaceCurrentItemWithPlayerItem(mediaItem)
            playerController.player!!.play()
        }
    }

}