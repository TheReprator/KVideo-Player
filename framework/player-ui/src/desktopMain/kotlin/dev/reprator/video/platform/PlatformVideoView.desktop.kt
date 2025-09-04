package dev.reprator.video.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import dev.reprator.kmp.video.platform.impl.PlaybackStateControllerImplJvm
import dev.reprator.kmp.video.platform.impl.PlayerController

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