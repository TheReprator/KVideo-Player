package dev.reprator.kmp.video.platform.impl

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dev.reprator.kmp.video.modals.VideoInitOptionModal

class PlaybackStateControllerImplAndroid(context: Context) : PlayerController {

    override lateinit var player: VideoPlayer

    val component: ExoPlayer by lazy {
        val player = ExoPlayer.Builder(context).build()
        player
    }

    override suspend fun setupPlayer(): Boolean {
        player = VideoPlayerImplAndroid(component)
        return true
    }

    override fun initPlayer(initOptions: VideoInitOptionModal) {
        component.setMediaItem(MediaItem.fromUri(initOptions.sources.first().src))
        component.prepare()
        component.play()
    }

}