package dev.reprator.video.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.reprator.kmp.video.platform.impl.PlayerController


@Composable
expect fun PlatformVideoView(
    playerController: PlayerController,
    modifier: Modifier = Modifier,
)