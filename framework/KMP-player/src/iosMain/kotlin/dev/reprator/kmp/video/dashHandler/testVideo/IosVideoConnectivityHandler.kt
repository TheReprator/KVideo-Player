package dev.reprator.kmp.video.dashHandler.testVideo

import dev.reprator.kmp.video.platform.dashHandler.DashHandler
import dev.reprator.kmp.video.platform.dashHandler.DashHandlerImpl
import kotlinx.cinterop.UnsafeNumber
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import platform.Foundation.NSError
import platform.WatchConnectivity.*
import platform.darwin.NSObject
import platform.darwin.TARGET_OS_WATCH

class IosVideoConnectivityHandler(private val dashHandler: DashHandler = DashHandlerImpl()) :
    VideoConnectivityHandler {

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
            when (val message =
                VideoConnectivityHandler.json.decodeFromString<VideoConnectivityMessage>(messageJson)) {
                is VideoConnectivityMessage.RequestPlayback -> {
                    handlePlaybackRequest(message) { response ->
                        println("Phone Vikram:: 3.2 session:: handlePlaybackRequest:: $response")
                        replyHandler(
                            mapOf(
                                "message" to VideoConnectivityHandler.json.encodeToString(
                                    response
                                )
                            )
                        )
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
        if ((TARGET_OS_WATCH == 1) && (WCSession.isSupported())) {
            println("Phone Vikram:: 0.1 session not supported, $TARGET_OS_WATCH")

            session = WCSession.defaultSession.apply {
                delegate = delegation
                activateSession()
            }
        } else {
            println("Phone Vikram:: 0.2 session not supported, $TARGET_OS_WATCH")
        }
    }

    override fun sendMessage(
        message: VideoConnectivityMessage,
        onResponse: (VideoConnectivityMessage) -> Unit
    ) {
        val messageJson = VideoConnectivityHandler.json.encodeToString(message)
        println("Phone Vikram:: 1.1 sendMessage $messageJson")

        if (TARGET_OS_WATCH == 1) {
            session?.sendMessage(
                mapOf("message" to messageJson),
                replyHandler = { handleResponse(it, onResponse) },
                errorHandler = { error ->
                    println("Phone Vikram:: 8 $error")
                    onResponse(
                        VideoConnectivityMessage.ErrorResponse(
                            error = error?.localizedDescription.orEmpty(),
                            requestId = (message as? VideoConnectivityMessage.RequestPlayback)?.requestId
                                ?: ""
                        )
                    )
                }
            )
        } else {
            val request = message as VideoConnectivityMessage.RequestPlayback
            dashHandler.playDashFile(request.mpdUrl) { avUrlAsset, sessionId ->
                val hlsUrl = avUrlAsset.URL.absoluteString
                if (hlsUrl != null)
                    onResponse(VideoConnectivityMessage.PlaybackResponse(hlsUrl, sessionId, request.requestId))
            }
        }
    }

    private fun handlePlaybackRequest(
        request: VideoConnectivityMessage.RequestPlayback,
        replyHandler: (VideoConnectivityMessage) -> Unit
    ) {
        dashHandler.playDashFile(request.mpdUrl) { avUrlAsset, sessionId ->
            val hlsUrl = avUrlAsset.URL.absoluteString

            if (hlsUrl != null) {
                replyHandler(VideoConnectivityMessage.PlaybackResponse(hlsUrl, sessionId, request.requestId))
            } else {
                replyHandler(
                    VideoConnectivityMessage.ErrorResponse(
                        "Failed to process MPD",
                        request.requestId
                    )
                )
            }
        }
    }

    private fun handleResponse(
        response: Map<Any?, *>?,
        onResponse: (VideoConnectivityMessage) -> Unit
    ) {
        println("Phone Vikram:: 4 handleResponse:: $response")

        val messageJson = response?.get("message") as? String ?: return
        val message =
            VideoConnectivityHandler.json.decodeFromString<VideoConnectivityMessage>(messageJson)
        onResponse(message)
    }
}