package dev.reprator.video.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import dev.reprator.video.modals.VideoInitOptionModal
import dev.reprator.video.modals.VideoSource
import dev.reprator.video.platform.impl.PlayerController
import dev.reprator.video.platform.impl.VideoPlayer
import dev.reprator.video.platform.ui.PlatformVideoView
import kvideo_player.videoframework.generated.resources.Res
import kvideo_player.videoframework.generated.resources.video_error_setup
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppVideoPlayer(stateController: PlayerController, modifier: Modifier = Modifier) {

    var isAppInitialized by remember { mutableStateOf<Boolean?>(null) }

    println("1 AppVideoPlayer 1")

    LaunchedEffect(Unit) {
        isAppInitialized = stateController.setupPlayer()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        println("1 AppVideoPlayer 1.1: $isAppInitialized")
        when (isAppInitialized) {
            null -> {
                CircularProgressIndicator()
            }
            false -> {
                Text(text = stringResource(Res.string.video_error_setup))
            }
            true -> {
                val videoSource = VideoSource("https://cdn.bitmovin.com/content/assets/sintel/sintel.mpd",
                "application/dash+xml", "")

                val videoInitOptions =
                    VideoInitOptionModal(poster = null, id = null, sources = listOf(videoSource))

                stateController.initPlayer(videoInitOptions)
                PrepareVideoSetup(stateController, modifier = modifier)
            }
        }
    }
}

@Composable
fun PrepareVideoSetup(playerController: PlayerController, modifier: Modifier = Modifier) {

    val player by lazy { playerController.player }

    Button(onClick = {
        changeVideoSource(player)
    }) {
        Text("Change Video Source")
    }

    Divider(Modifier.height(50.dp).fillMaxWidth())

    PlatformVideoView(playerController, Modifier.height(300.dp).width(300.dp))

    DisposableEffect(LocalLifecycleOwner.current) {
        onDispose {
            println("2 handleVideoView 2")
            if (!player.isDisposed()) {
                player.dispose()
            }
        }
    }
}

fun changeVideoSource(player: VideoPlayer) {
    val videoSource = VideoSource(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "video/mp4", "https://i.ytimg.com/vi/aqz-KE-bpKQ/maxresdefault.jpg"
    )
    player.chaneMedia(videoSource)
}