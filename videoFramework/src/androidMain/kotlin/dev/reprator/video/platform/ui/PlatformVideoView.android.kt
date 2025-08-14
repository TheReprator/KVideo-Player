package dev.reprator.video.platform.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.media3.ui.compose.PlayerSurface
import dev.reprator.video.platform.impl.PlaybackStateControllerImplAndroid
import dev.reprator.video.platform.impl.PlayerController

@Composable
actual fun PlatformVideoView(
    playerController: PlayerController,
    modifier: Modifier
) {
    val exoPlayer = remember { (playerController as PlaybackStateControllerImplAndroid).component }

    Box(
        modifier = modifier
    ) {
        PlayerSurface(
            player = exoPlayer,
            modifier = Modifier.fillMaxSize()
        )
    }
}