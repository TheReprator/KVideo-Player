package dev.reprator.video.ui

import androidx.annotation.OptIn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_TEXTURE_VIEW
import androidx.media3.ui.compose.modifiers.resizeWithContentScale
import androidx.tv.material3.Text
import androidx.tv.material3.MaterialTheme
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
                Box(modifier = modifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.message_loading),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
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

@OptIn(UnstableApi::class)
@Composable
private fun PrepareVideoSetup(playerController: PlayerController, modifier: Modifier = Modifier) {

    val exoPlayer = remember {
        (playerController as PlaybackStateControllerImplAndroid).component
    }

    PlayerSurface(
        player = exoPlayer,
        surfaceType = SURFACE_TYPE_TEXTURE_VIEW,
        modifier = Modifier.resizeWithContentScale(
            contentScale = ContentScale.Fit,
            sourceSizeDp = null
        )
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

//https://github.com/android/tv-samples/blob/main/JetStreamCompose/