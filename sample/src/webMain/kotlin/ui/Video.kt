@file:OptIn(ExperimentalWasmJsInterop::class)

package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.reprator.video.demo.VideoScreen
import dev.reprator.video.platform.impl.PlaybackStateControllerImplWeb
import dev.reprator.video.platform.impl.PlayerController
import kotlin.js.ExperimentalWasmJsInterop

@Composable
fun AppVideoPlayer() {
    MaterialTheme {
        VideoScreen(PlaybackStateControllerImplWeb())
    }
}