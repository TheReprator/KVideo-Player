package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityHandler
import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityMessage
import dev.reprator.kmp.video.modals.VideoInitOptionModal
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

class PlaybackStateControllerImplIos(private val connectivityHandler: VideoConnectivityHandler) :
    PlayerController {

    override lateinit var player: VideoPlayer

    val playerController: AVPlayerViewController by lazy {
        val controller = AVPlayerViewController()
        controller.player = AVPlayer()
        controller
    }

    override suspend fun setupPlayer(): Boolean {
        player = VideoPlayerImplIos(connectivityHandler, playerController)
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {

        val videoSource = initOptions.sources.first()
        val url = videoSource.src

        if (url.endsWith(".mpd")) {
            val request = VideoConnectivityMessage.RequestPlayback(videoSource.src)

            connectivityHandler.sendMessage(request) { response ->
                println("VideoMPD Vikram:: 1 $response")

                when (response) {
                    is VideoConnectivityMessage.PlaybackResponse -> {
                        val fileURL = NSURL(fileURLWithPath = response.hlsUrl, false)

                        val urlAsset = AVURLAsset(uRL = fileURL, options = null)

                        playerController.showsPlaybackControls = true
                        playerController.player!!.replaceCurrentItemWithPlayerItem(
                            AVPlayerItem(
                                urlAsset
                            )
                        )
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
            val mediaItem = AVPlayerItem(NSURL.URLWithString(url)!!)
            playerController.showsPlaybackControls = true
            playerController.player!!.replaceCurrentItemWithPlayerItem(mediaItem)
            playerController.player!!.play()
        }
    }
}