package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.modals.VideoSource
import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityHandler
import dev.reprator.kmp.video.dashHandler.testVideo.VideoConnectivityMessage
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.Foundation.NSURL

class VideoPlayerImplWatch(private val player: AVPlayer, private val connectivityHandler: VideoConnectivityHandler) : VideoPlayer {

    private var isDisposed = false

    override fun play() {
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun isDisposed(): Boolean {
        return isDisposed
    }

    override fun dispose() {
        player.pause()
        player.replaceCurrentItemWithPlayerItem(null)

        isDisposed = true
    }

    override fun changeMedia(videoSource: VideoSource) {
        if (videoSource.src.endsWith(".mpd")) {

            val request = VideoConnectivityMessage.RequestPlayback(videoSource.src)

            connectivityHandler.sendMessage(request) { response ->
                when (response) {
                    is VideoConnectivityMessage.PlaybackResponse -> {
                        NSURL.URLWithString(response.hlsUrl)?.let { url ->
                            val playerItem = AVPlayerItem(url)
                            player.replaceCurrentItemWithPlayerItem(playerItem)
                            player.play()
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
            val mediaItem = AVPlayerItem(NSURL.URLWithString(videoSource.src)!!)
            player.replaceCurrentItemWithPlayerItem(mediaItem)
            player.play()
        }
    }

}
