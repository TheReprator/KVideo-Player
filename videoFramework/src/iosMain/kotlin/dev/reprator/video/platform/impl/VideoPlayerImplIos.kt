package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoSource
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

class VideoPlayerImplIos(private val playerController: AVPlayerViewController): VideoPlayer {

    private var isDisposed = false

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
        val mediaItem = AVPlayerItem(NSURL.URLWithString(videoSource.src)!!)
        playerController.player!!.replaceCurrentItemWithPlayerItem(mediaItem)
        playerController.player!!.play()
    }

}