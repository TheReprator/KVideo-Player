@file:OptIn(ExperimentalWasmJsInterop::class)

package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.reprator.video.platform.impl.PlaybackStateControllerImpl
import dev.reprator.video.ui.AppVideoPlayer
import kotlin.js.ExperimentalWasmJsInterop

@Composable
fun AppVideoPlayer() {
    MaterialTheme {
        AppVideoPlayer(PlaybackStateControllerImpl())
    }
}