package dev.reprator.video.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() =
    application {
            Window(
                onCloseRequest = ::exitApplication,
                title = "Movies",
            ) {
                window.minimumSize = Dimension(350, 600)
                VideoPlayerImpl(
                    "https://cdn.bitmovin.com/content/assets/sintel/hls/playlist.m3u8",
                    Modifier.background(Color.Red).fillMaxSize()
                )
            }
    }
