@file:OptIn(ExperimentalWasmJsInterop::class)

package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.reprator.video.demo.VideoScreen
import kotlin.js.ExperimentalWasmJsInterop

@Composable
fun AppVideoPlayer() {
    MaterialTheme {
        VideoScreen()
    }
}