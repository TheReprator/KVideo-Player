package dev.reprator.video.platform.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import dev.reprator.video.platform.impl.PlaybackStateControllerImplIos
import dev.reprator.video.platform.impl.PlayerController
import platform.UIKit.UIView

@Composable
actual fun PlatformVideoView(
    playerController: PlayerController,
    modifier: Modifier
) {

    val avPlayerViewController = remember { (playerController as PlaybackStateControllerImplIos).playerController }

    UIKitView(
        factory = {
            // Create a UIView to hold the AVPlayerLayer
            val playerContainer = UIView()
            playerContainer.addSubview(avPlayerViewController.view)
            // Return the playerContainer as the root UIView
            playerContainer
        },
        modifier = modifier)
}