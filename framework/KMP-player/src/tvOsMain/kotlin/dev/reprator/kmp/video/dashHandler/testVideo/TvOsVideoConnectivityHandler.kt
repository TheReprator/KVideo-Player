package dev.reprator.kmp.video.dashHandler.testVideo

import dev.reprator.kmp.video.platform.dashHandler.DashHandler
import dev.reprator.kmp.video.platform.dashHandler.DashHandlerImpl

class TvOsVideoConnectivityHandler(private val dashHandler: DashHandler = DashHandlerImpl()) :
    VideoConnectivityHandler {

    override fun sendMessage(
        message: VideoConnectivityMessage,
        onResponse: (VideoConnectivityMessage) -> Unit
    ) {
        val request = message as VideoConnectivityMessage.RequestPlayback
        println("TVOS Vikram:: 1.1 sendMessage $request")

        dashHandler.playDashFile(request.mpdUrl) { avUrlAsset, sessionId ->
            val hlsUrl = avUrlAsset.URL.absoluteString
            println("TVOS Vikram:: 1.2 processes response $hlsUrl")

            val response =
                VideoConnectivityMessage.PlaybackResponse(hlsUrl ?: "", sessionId, request.requestId)
            onResponse(response)
        }
    }
}