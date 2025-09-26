package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityHandler
import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityMessage
import dev.reprator.kmp.video.dashHandler.testVideo.WatchVideoConnectivityHandler
import dev.reprator.kmp.video.modals.VideoInitOptionModal
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.Foundation.NSURL

class PlaybackStateControllerImplWatch(private val connectivityHandler: VideoConnectivityHandler = WatchVideoConnectivityHandler()) : PlayerController {

    override lateinit var player: VideoPlayer

    private val aVPlayer: AVPlayer by lazy {
        AVPlayer()
    }

    override suspend fun setupPlayer(): Boolean {
        player = VideoPlayerImplWatch(aVPlayer, connectivityHandler)
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        val videoSource = initOptions.sources.first()
        val url = videoSource.src

        if (url.endsWith(".mpd")) {
            val request = VideoConnectivityMessage.RequestPlayback(videoSource.src)

            connectivityHandler.sendMessage(request) { response ->
                when (response) {
                    is VideoConnectivityMessage.PlaybackResponse -> {
                        NSURL.URLWithString(response.hlsUrl)?.let { url ->
                            val playerItem = AVPlayerItem(url)
                            aVPlayer.replaceCurrentItemWithPlayerItem(playerItem)
                            aVPlayer.play()
                        }
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
            aVPlayer.replaceCurrentItemWithPlayerItem(mediaItem)
            aVPlayer.play()
        }
    }
}
