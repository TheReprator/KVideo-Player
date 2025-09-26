package dev.reprator.kmp.video.dashHandler.testVideo

interface VideoConnectivityHandler {
    fun sendMessage(message: VideoConnectivityMessage, onResponse: (VideoConnectivityMessage) -> Unit)
}