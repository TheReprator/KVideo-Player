package dev.reprator.kmp.video.platform.impl

import android.content.Context
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import dev.reprator.kmp.video.modals.VideoInitOptionModal

@UnstableApi
class PlaybackStateControllerImplAndroid(context: Context) : PlayerController {

    override lateinit var player: VideoPlayer

    val component: ExoPlayer by lazy {
        val player = ExoPlayer.Builder(context).setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
            .build()
            .apply {
                repeatMode = Player.REPEAT_MODE_OFF
            }
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