@file:OptIn(ExperimentalWasmJsInterop::class)

package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
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
import com.hamama.kwhi.HtmlView
import external.videojs
import modals.VideoPlayer
import utils.VIDEO_JS_CSS_ID
import utils.VIDEO_JS_CSS_URL
import utils.VIDEO_JS_ID
import utils.VIDEO_JS_URL
import utils.appGetElementById
import utils.createVideoOptionsObject
import utils.createVideoSource
import utils.isVideoJsFuncAvailable
import utils.loadCss
import utils.loadJsScript
import web.cssom.ClassName
import web.dom.ElementId
import web.dom.document
import web.events.Event
import web.events.EventHandler
import web.events.OFFLINE
import web.events.ONLINE
import web.events.addEventListener
import web.window.window
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsException
import kotlin.random.Random

@Composable
fun AppVideoPlayer() {

    println("1 AppVideoPlayer 1")

    MaterialTheme {
        var isError by remember { mutableStateOf<Boolean?>(null) }

        println("1 AppVideoPlayer 2")

        LaunchedEffect(Unit) {
            try {
                loadJsScript(VIDEO_JS_URL, VIDEO_JS_ID)
                loadCss(VIDEO_JS_CSS_URL, VIDEO_JS_CSS_ID)
                if (isVideoJsFuncAvailable()) {
                    println("1 AppVideoPlayer 3")
                    isError = false
                }
            } catch (e: JsException) {
                isError = true
                println("1 AppVideoPlayer 3.1 error: ${e.message}")
                e.printStackTrace()
            } catch (e: Exception) {
                isError = true
                println("1 AppVideoPlayer 3.2 error: ${e.message}")
                e.printStackTrace()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            println("1 AppVideoPlayer 4")
            if (null != isError) {
                println("1 AppVideoPlayer 4.1")
                if (true == isError) {
                    Text(text = "An error occured while loading video script and css while downloading from server. Please referesh")
                } else {
                    PrepareVideoSetup()
                }
            }
        }
    }
}

@Composable
fun PrepareVideoSetup() {
    println("2 handleVideoView ")

    val videoElementId = remember { "dynamic-video-js-${Random.nextInt()}" }
    var player by remember { mutableStateOf<VideoPlayer?>(null) } // Hold the player instance

    Button(onClick = {
        changeVideoSource(player!!)
    }) {
        Text("Change Video Source")
    }

    Divider(Modifier.height(50.dp).fillMaxWidth())

    VideoScreen(videoElementId, {
        println("2 handleVideoView 1")
        player = this
        listenErrorEvents(player!!)
    })

    DisposableEffect(LocalLifecycleOwner.current) {
        onDispose {
            println("2 handleVideoView 2")
            if (null == player) {
                return@onDispose
            }
            player!!.off()
            if (false == player!!.isDisposed()) {
                player!!.dispose()
            }

            val videoCssModule = appGetElementById(VIDEO_JS_CSS_ID) ?: return@onDispose
            document.head.removeChild(videoCssModule)

            val videoJsModule = appGetElementById(VIDEO_JS_ID) ?: return@onDispose
            document.head.removeChild(videoJsModule)
        }
    }
}


@Composable
fun VideoScreen(videoElementId: String, updatePlayer: VideoPlayer?.() -> Unit) {

    println("3 VideoScreen")

    val videoOptions by remember {
        mutableStateOf(
            createVideoOptionsObject(
                "https://cdn.bitmovin.com/content/assets/sintel/sintel.mpd",
                "application/dash+xml"
            )
        )
    }

    HtmlView(
        modifier = Modifier.width(600.dp).height(500.dp),
        factory = {
            println("3 VideoScreen HtmlView 1")
            val videoElement = document.createElement("video-js")
            videoElement.id = ElementId(videoElementId)
            videoElement.className = ClassName("vjs-big-play-centered")
            videoElement
        },
        update = { videoElement ->
            println("3 VideoScreen HtmlView 2")
            if ((videoElement.getAttribute("data-vjs-player") == null)) {
                println("3 VideoScreen HtmlView 2.1")
                val newVidePlayer = videojs(videoElement, videoOptions)
                updatePlayer(newVidePlayer)
                videoElement.setAttribute("data-vjs-player", "true")
            }
        }
    )
}

fun changeVideoSource(player: VideoPlayer) {
    println("6 inside update App changeVideoSource")
    val src = createVideoSource(
        videoSrcUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        videoType = "video/mp4"
    )
    player.pause()
    player.poster("https://i.ytimg.com/vi/aqz-KE-bpKQ/maxresdefault.jpg")
    player.src(src)
}

fun listenErrorEvents(player: VideoPlayer) {
    println("4 listenErrorEvents")

    player.on("error") {
        println("EVENT: Vikram error, Player: ")
    }

    player.on("play") {
        println("EVENT: Vikram play, Player:")
    }

    player.on("playing") {
        println("EVENT: Vikram playing, Player: $")
    }

    player.on("ended") {
        println("EVENT: Vikram ended, Player:")
    }

    player.on("waiting") { // When buffering causes a pause
        println("EVENT: Vikram waiting (buffering started), Player: ")
    }

    player.on("stalled") { // When the browser is trying to fetch data but it's not coming
        println("EVENT: Vikram stalled, Player: ")
    }

    player.on("buffer_start") { // When the browser is trying to fetch data but it's not coming
        println("EVENT: Vikram buffer_start, Player: ")
    }

    window.addEventListener(Event.OFFLINE, EventHandler { event: Event ->
        println("Internet connection lost!")
        player.pause()
    })


    window.addEventListener(Event.ONLINE, EventHandler { event: Event ->
        println("'Internet connection present!'")
        player.play()
    })
}