package dev.reprator.kmp.video.platform.impl

import dev.reprator.kmp.video.modals.VideoInitOptionModal


interface PlayerController {
    val player: VideoPlayer
    suspend fun setupPlayer(): Boolean
    fun initPlayer(initOptions: VideoInitOptionModal)
}
