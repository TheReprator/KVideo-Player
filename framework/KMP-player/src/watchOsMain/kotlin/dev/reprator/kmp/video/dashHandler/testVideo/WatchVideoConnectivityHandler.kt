package dev.reprator.kmp.video.dashHandler.testVideo

import kotlinx.cinterop.UnsafeNumber
import kotlinx.serialization.json.Json
import platform.Foundation.NSError
import platform.WatchConnectivity.*
import platform.darwin.NSObject

class WatchVideoConnectivityHandler : VideoConnectivityHandler {

    private var session: WCSession? = null

    @OptIn(UnsafeNumber::class)
    private val delegation = object : NSObject(),WCSessionDelegateProtocol {

        override fun session(
            session: WCSession,
            activationDidCompleteWithState: WCSessionActivationState,
            error: NSError?
        ) {
            println("Watch Vikram:: 3. session:: $error")
        }
    }

    init {
        if (WCSession.isSupported()) {
            session = WCSession.defaultSession.apply {
                delegate = delegation
                activateSession()
            }
        } else {
            println("Watch Vikram:: 1. session not supported")
        }
    }

    override fun sendMessage(
        message: VideoConnectivityMessage,
        onResponse: (VideoConnectivityMessage) -> Unit
    ) {
        val messageJson = Json.encodeToString(message)
        println("Watch Vikram:: 1.1 sendMessage $messageJson")

        session?.sendMessage(
            mapOf("message" to messageJson),
            replyHandler = { handleResponse(it, onResponse) },
            errorHandler = { error ->
                println("Watch Vikram:: 8 $error")
                onResponse(
                    VideoConnectivityMessage.ErrorResponse(
                        error = error?.localizedDescription.orEmpty(),
                        requestId = (message as? VideoConnectivityMessage.RequestPlayback)?.requestId ?: ""
                    )
                )
            }
        )
    }

    private fun handleResponse(
        response: Map<Any?, *>?,
        onResponse: (VideoConnectivityMessage) -> Unit
    ) {
        println("Watch Vikram:: 4 handleResponse:: $response")
        val messageJson = response?.get("message") as? String ?: return
        val message = Json.decodeFromString<VideoConnectivityMessage>(messageJson)
        onResponse(message)
    }
}