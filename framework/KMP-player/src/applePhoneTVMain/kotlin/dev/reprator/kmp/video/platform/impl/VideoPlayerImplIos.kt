package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityHandler
import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityMessage
import dev.reprator.kmp.video.modals.VideoSource
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

class VideoPlayerImplIos(private val connectivityHandler: VideoConnectivityHandler, private val playerController: AVPlayerViewController) : VideoPlayer {

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
        if (videoSource.src.endsWith(".mpd")) {
            val request = VideoConnectivityMessage.RequestPlayback(videoSource.src)
            connectivityHandler.sendMessage(request) { response ->
                println("VideoMPD Vikram:: 2 $response")
                when (response) {
                    is VideoConnectivityMessage.PlaybackResponse -> {
                        val fileURL = NSURL(fileURLWithPath = response.hlsUrl, false)

                        val urlAsset = AVURLAsset(uRL = fileURL, options = null)

                            playerController.showsPlaybackControls = true
                            playerController.player!!.replaceCurrentItemWithPlayerItem(AVPlayerItem(urlAsset))
                            playerController.player!!.play()

                    }
                    is VideoConnectivityMessage.ErrorResponse -> {
                        println("Error playing video: ${response.error}")
                    }
                    else -> {

                    }
                }
            }
        } else {
            val mediaItem = AVPlayerItem(NSURL.URLWithString(videoSource.src)!!)
            playerController.player!!.replaceCurrentItemWithPlayerItem(mediaItem)
            playerController.player!!.play()
        }
    }

}