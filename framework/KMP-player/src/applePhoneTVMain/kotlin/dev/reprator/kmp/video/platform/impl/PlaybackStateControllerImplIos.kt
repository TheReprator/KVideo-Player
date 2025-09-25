package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.modals.VideoInitOptionModal
import dev.reprator.kmp.video.dashHandler.DashHandler
import dev.reprator.kmp.video.platform.assetHandler.AVAssetResourceHandlerImpl
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

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

    private val dashHandler: DashHandler by lazy {
        DashHandler.getDashHandler(AVAssetResourceHandlerImpl())
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {

        val videoSource = initOptions.sources.first()
        val url = videoSource.src

        if (url.endsWith(".mpd")) {
            dashHandler.playDashFile(url) {
                playerController.showsPlaybackControls = true
                playerController.player!!.replaceCurrentItemWithPlayerItem(this)
                playerController.player!!.play()
            }
        } else {
            val mediaItem = AVPlayerItem(NSURL.URLWithString(url)!!)
            playerController.showsPlaybackControls = true
            playerController.player!!.replaceCurrentItemWithPlayerItem(mediaItem)
            playerController.player!!.play()
        }
    }
}