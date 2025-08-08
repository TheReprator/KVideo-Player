package dev.reprator.video.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.reprator.video.platform.impl.PlaybackStateController

@Composable
fun AppEntry(stateController: PlaybackStateController) {

    MaterialTheme {
        AppVideoPlayer(stateController)
    }
}