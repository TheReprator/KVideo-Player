package dev.reprator.video.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import dev.reprator.video.modals.VideoInitOptionModal
import dev.reprator.video.platform.impl.PlayerController
import dev.reprator.video.platform.ui.PlatformVideoView
import kvideo_player.videoframework.generated.resources.Res
import kvideo_player.videoframework.generated.resources.video_error_setup
import org.jetbrains.compose.resources.stringResource

@Composable
fun KVideoPlayer(
    stateController: PlayerController, videoInitOptions: VideoInitOptionModal,
    modifier: Modifier = Modifier
) {

    var isAppInitialized by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        isAppInitialized = stateController.setupPlayer()
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (isAppInitialized) {
            null -> {
                CircularProgressIndicator()
            }

            false -> {
                Text(text = stringResource(Res.string.video_error_setup))
            }

            true -> {
                stateController.initPlayer(videoInitOptions)
                PrepareVideoSetup(stateController, modifier = Modifier)
            }
        }
    }
}

@Composable
private fun PrepareVideoSetup(playerController: PlayerController, modifier: Modifier = Modifier) {

    PlatformVideoView(playerController, modifier.fillMaxSize())

    DisposableEffect(LocalLifecycleOwner.current) {
        onDispose {
            val player = playerController.player

            if (!player.isDisposed()) {
                player.dispose()
            }
        }
    }
}