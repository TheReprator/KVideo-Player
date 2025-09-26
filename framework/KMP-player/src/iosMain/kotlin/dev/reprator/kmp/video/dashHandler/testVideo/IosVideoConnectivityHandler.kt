package dev.reprator.kmp.video.dashHandler.testVideo

import dev.reprator.kmp.video.platform.dashHandler.DashHandler
import dev.reprator.kmp.video.platform.dashHandler.DashHandlerImpl
import kotlinx.cinterop.UnsafeNumber
import kotlinx.serialization.json.Json
import platform.Foundation.NSError
import platform.WatchConnectivity.*
import platform.darwin.NSObject

class IosVideoConnectivityHandler (private val dashHandler: DashHandler = DashHandlerImpl()): VideoConnectivityHandler {

    private var session: WCSession? = null

    @OptIn(UnsafeNumber::class)
    private val delegation = object : NSObject(), WCSessionDelegateProtocol {

        override fun session(
            session: WCSession,
            didReceiveMessage: Map<Any?, *>,
            replyHandler: (Map<Any?, *>?) -> Unit
        ) {
            println("Phone Vikram:: 3. session::")
            val messageJson = didReceiveMessage["message"] as? String ?: return
            println("Phone Vikram:: 3.1 session:: $messageJson")
            when (val message = Json.decodeFromString<VideoConnectivityMessage>( messageJson)) {
                is VideoConnectivityMessage.RequestPlayback -> {
                    handlePlaybackRequest(message) { response ->
                        println("Phone Vikram:: 3.2 session:: handlePlaybackRequest:: $response")
                        replyHandler(mapOf("message" to Json.encodeToString(response)))
                    }
                }
                else -> {

                }
            }
        }

        override fun sessionDidBecomeInactive(session: WCSession) {
            println("Phone Vikram:: 7 session::")
        }

        override fun session(
            session: WCSession,
            activationDidCompleteWithState: WCSessionActivationState,
            error: NSError?
        ) {
            println("Phone Vikram:: 5 session:: $error")
        }

        override fun sessionDidDeactivate(session: WCSession) {
            println("Phone Vikram:: 6 session::")
        }
    }

    init {
        if (WCSession.isSupported()) {
            session = WCSession.defaultSession.apply {
                delegate = delegation
                activateSession()
            }
        } else {
            println("Phone Vikram:: 1. session not supported")
        }
    }

    override fun sendMessage(
        message: VideoConnectivityMessage,
        onResponse: (VideoConnectivityMessage) -> Unit
    ) {
        val messageJson = Json.encodeToString(message)
        println("Phone Vikram:: 1.1 sendMessage $messageJson")

        session?.sendMessage(
            mapOf("message" to messageJson),
            replyHandler = { handleResponse(it, onResponse) },
            errorHandler = { error ->
                println("Phone Vikram:: 8 $error")
                onResponse(
                    VideoConnectivityMessage.ErrorResponse(
                        error = error?.localizedDescription.orEmpty(),
                        requestId = (message as? VideoConnectivityMessage.RequestPlayback)?.requestId ?: ""
                    )
                )
            }
        )
    }

    private fun handlePlaybackRequest(
        request: VideoConnectivityMessage.RequestPlayback,
        replyHandler: (VideoConnectivityMessage) -> Unit
    ) {
        dashHandler.playDashFile(request.mpdUrl) {
            val hlsUrl = this.URL.absoluteString

            if (hlsUrl != null) {
                replyHandler(VideoConnectivityMessage.PlaybackResponse(hlsUrl, request.requestId))
            } else {
                replyHandler(VideoConnectivityMessage.ErrorResponse("Failed to process MPD", request.requestId))
            }
        }
    }

    private fun handleResponse(
        response: Map<Any?, *>?,
        onResponse: (VideoConnectivityMessage) -> Unit
    ) {
        println("Phone Vikram:: 4 handleResponse:: $response")

        val messageJson = response?.get("message") as? String ?: return
        val message = Json.decodeFromString<VideoConnectivityMessage>(messageJson)
        onResponse(message)
    }
}