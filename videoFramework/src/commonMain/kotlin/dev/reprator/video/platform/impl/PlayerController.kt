package dev.reprator.video.platform.impl

import dev.reprator.video.modals.VideoInitOptionModal


interface PlayerController {
    val player: VideoPlayer
    suspend fun setupPlayer(): Boolean
    fun initPlayer(initOptions: VideoInitOptionModal)
}
