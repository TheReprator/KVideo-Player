package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoSource

interface VideoPlayer {
    fun play()
    fun pause()
    fun isDisposed(): Boolean
    fun dispose()
    fun chaneMedia(videoSource: VideoSource)
}