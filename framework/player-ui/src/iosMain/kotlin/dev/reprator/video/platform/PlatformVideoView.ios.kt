package dev.reprator.video.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import dev.reprator.kmp.video.platform.impl.PlaybackStateControllerImplIos
import dev.reprator.kmp.video.platform.impl.PlayerController
import platform.UIKit.NSLayoutConstraint
import platform.UIKit.UIView

@Composable
actual fun PlatformVideoView(
    playerController: PlayerController,
    modifier: Modifier
) {

    val avPlayerViewController = remember { (playerController as PlaybackStateControllerImplIos).playerController }

    UIKitView(
        factory = {
            val playerContainer = UIView()
            val playerView = avPlayerViewController.view

            playerView.translatesAutoresizingMaskIntoConstraints = false
            playerContainer.addSubview(playerView)

            NSLayoutConstraint.activateConstraints(
                listOf(
                    playerView.topAnchor.constraintEqualToAnchor(playerContainer.topAnchor),
                    playerView.bottomAnchor.constraintEqualToAnchor(playerContainer.bottomAnchor),
                    playerView.leadingAnchor.constraintEqualToAnchor(playerContainer.leadingAnchor),
                    playerView.trailingAnchor.constraintEqualToAnchor(playerContainer.trailingAnchor),
                )
            )

            playerContainer
        },
        modifier = modifier)
}