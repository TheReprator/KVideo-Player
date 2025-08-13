package dev.reprator.video.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.reprator.video.modals.VideoInitOptionModal
import dev.reprator.video.modals.VideoSource
import dev.reprator.video.platform.impl.PlayerController
import dev.reprator.video.platform.impl.VideoPlayer
import dev.reprator.video.ui.KVideoPlayer


val videoSource = VideoSource(
    "https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd", ""
)

val videoInitOptions =
    VideoInitOptionModal(poster = null, id = null, sources = listOf(videoSource))

@Composable
fun VideoScreen(playerController: PlayerController) {

    val videoInitOptions by remember {
        mutableStateOf(videoInitOptions)
    }

    val mediaController by remember {
        mutableStateOf(playerController)
    }

    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
           changeVideoSource(mediaController.player)
        }) {
            Text("Change Video Source")
        }

        Divider(Modifier.height(50.dp).fillMaxWidth())

        KVideoPlayer(
            mediaController,
            videoInitOptions,
            Modifier.fillMaxSize()
        )
    }
}

fun changeVideoSource(player: VideoPlayer) {
    val videoSource = VideoSource(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "https://i.ytimg.com/vi/aqz-KE-bpKQ/maxresdefault.jpg"
    )
    player.changeMedia(videoSource)
}



