package dev.reprator.kmp.video.dashHandler.testVideo

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

interface VideoConnectivityHandler {
    companion object {
        val json = Json {
            serializersModule = SerializersModule {
                polymorphic(VideoConnectivityMessage::class) {
                    subclass(VideoConnectivityMessage.RequestPlayback::class)
                    subclass(VideoConnectivityMessage.PlaybackResponse::class)
                    subclass(VideoConnectivityMessage.ErrorResponse::class)
                }
            }
        }
    }
    fun sendMessage(message: VideoConnectivityMessage, onResponse: (VideoConnectivityMessage) -> Unit)
}