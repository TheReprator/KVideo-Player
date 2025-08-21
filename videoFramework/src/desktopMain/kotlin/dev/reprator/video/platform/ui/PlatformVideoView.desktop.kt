package dev.reprator.video.platform.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import dev.reprator.video.platform.impl.PlaybackStateControllerImplJvm
import dev.reprator.video.platform.impl.PlayerController
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.layout.BorderPane

@Composable
actual fun PlatformVideoView(
    playerController: PlayerController,
    modifier: Modifier
) {
    val mediaView = remember {  (playerController as PlaybackStateControllerImplJvm).mediaView  }
    val jfxPanel by remember {
        mutableStateOf(JFXPanel())
    }

    SwingPanel(
        factory = {

            val root = BorderPane()
            root.center = mediaView

            root.widthProperty().addListener { _, _, newValue ->
                mediaView.fitWidth = newValue.toDouble()
            }
            root.heightProperty().addListener { _, _, newValue ->
                mediaView.fitHeight = newValue.toDouble()
            }

            val scene = Scene(root)

            jfxPanel.apply {
                this.scene = scene
            }
        },
        background = Color.Transparent,
        modifier = modifier
    )
}