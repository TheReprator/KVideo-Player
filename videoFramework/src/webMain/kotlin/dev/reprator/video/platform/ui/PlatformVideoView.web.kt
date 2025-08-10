package dev.reprator.video.platform.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.reprator.video.platform.impl.PlaybackStateControllerImplWeb
import dev.reprator.video.platform.impl.PlayerController
import dev.reprator.video.webInterlop.HtmlView

@Composable
actual fun PlatformVideoView(
    playerController: PlayerController,
    modifier: Modifier,
) {
    val webPlayerController = playerController as PlaybackStateControllerImplWeb

    HtmlView(
        modifier = modifier,
        factory = {
            webPlayerController.videoElement.value
        },
        /*update = { videoElement ->
            if ((videoElement.getAttribute("data-vjs-player") == null)) {
                videoElement.setAttribute("data-vjs-player", "true")
            }
        }*/
        //Not needed
    )
}