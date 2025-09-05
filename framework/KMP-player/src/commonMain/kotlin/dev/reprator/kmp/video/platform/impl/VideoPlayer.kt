package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.modals.VideoSource

interface VideoPlayer {
    fun play()
    fun pause()
    fun isDisposed(): Boolean
    fun dispose()
    fun changeMedia(videoSource: VideoSource)
}