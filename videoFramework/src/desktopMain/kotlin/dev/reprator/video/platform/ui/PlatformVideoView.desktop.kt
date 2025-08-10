package dev.reprator.video.platform.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import dev.reprator.video.platform.impl.PlaybackStateControllerImplJvm
import dev.reprator.video.platform.impl.PlayerController

@Composable
actual fun PlatformVideoView(
    playerController: PlayerController,
    modifier: Modifier
) {
    val factory = remember { { (playerController as PlaybackStateControllerImplJvm).component } }

    SwingPanel(
        factory = factory,
        background = Color.Transparent,
        modifier = modifier
    )
}