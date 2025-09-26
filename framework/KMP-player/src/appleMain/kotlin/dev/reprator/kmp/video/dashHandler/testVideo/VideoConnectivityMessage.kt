package dev.reprator.kmp.video.dashHandler.testVideo

import kotlinx.serialization.Serializable
import platform.Foundation.NSUUID.Companion.UUID

sealed interface VideoConnectivityMessage {
    @Serializable
    data class RequestPlayback(
        val mpdUrl: String,
        val requestId: String = UUID().UUIDString()
    ) : VideoConnectivityMessage

    @Serializable
    data class PlaybackResponse(
        val hlsUrl: String,
        val requestId: String
    ) : VideoConnectivityMessage

    @Serializable
    data class ErrorResponse(
        val error: String,
        val requestId: String
    ) : VideoConnectivityMessage
}