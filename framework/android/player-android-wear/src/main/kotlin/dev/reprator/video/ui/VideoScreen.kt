package dev.reprator.video.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material3.CircularProgressIndicator
import androidx.wear.compose.material3.Text
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.media.ui.screens.player.PlayerScreen
import androidx.media3.ui.compose.PlayerSurface // For displaying the video
import dev.reprator.kmp.video.modals.VideoInitOptionModal
import dev.reprator.kmp.video.platform.impl.PlaybackStateControllerImplAndroid
import dev.reprator.kmp.video.platform.impl.PlayerController
import dev.reprator.video.R

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
                Text(text = stringResource(R.string.video_error_setup))
            }

            true -> {
                stateController.initPlayer(videoInitOptions)
                PrepareVideoSetup(stateController, modifier = modifier)
            }
        }
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
private fun PrepareVideoSetup(playerController: PlayerController, modifier: Modifier = Modifier) {

    val exoPlayer = remember {
        (playerController as PlaybackStateControllerImplAndroid).component
    }

    PlayerScreen(
        modifier = modifier.fillMaxSize(),
        mediaDisplay = {
            PlayerSurface(
                player = exoPlayer,
                modifier = Modifier.fillMaxSize()
            )
        },
        controlButtons = {

        },
        buttons = {

        }
    )

    DisposableEffect(LocalLifecycleOwner.current) {
        onDispose {
            val player = playerController.player
            if (!player.isDisposed()) {
                player.dispose()
            }
        }
    }
}

//https://google.github.io/horologist/simple-media-app-guide/