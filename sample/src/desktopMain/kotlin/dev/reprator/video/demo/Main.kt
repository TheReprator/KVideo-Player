package dev.reprator.video.demo

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.reprator.video.platform.impl.PlaybackStateControllerImplJvm
import java.awt.Dimension

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Video JVM",
        ) {
            window.minimumSize = Dimension(350, 600)

            MaterialTheme {
                VideoScreen(PlaybackStateControllerImplJvm())
            }
        }
    }
