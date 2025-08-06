import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.hamama.kwhi.HtmlView
import external.Player
import external.VideoJsOptions
import external.VideoSource
import external.videojs
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import util.isVideoJsFuncAvailable
import util.loadCss
import util.loadJs
import util.newJsObject
import kotlin.js.unsafeCast

const val VIDEO_JS_URL = "https://vjs.zencdn.net/8.6.1/video.min.js"
const val VIDEO_JS_CSS_URL = "https://vjs.zencdn.net/8.6.1/video-js.css"

@Composable
fun App() {
    println("1 inside update App")

    MaterialTheme {
        var isPlayerLibraryReady by remember { mutableStateOf(false) }
        val videoElementId = remember { "dynamic-video-js-${kotlin.random.Random.nextInt()}" }
        var player by remember { mutableStateOf<Player?>(null) } // Hold the player instance
        println("2 inside update App MaterialTheme")

        LaunchedEffect(Unit) {
            loadJs(VIDEO_JS_URL)
            loadCss(VIDEO_JS_CSS_URL)
            if (isVideoJsFuncAvailable()) {
                println("SUCCESS: videojs function IS available globally.")
                isPlayerLibraryReady = true
            }
        }

        val scope =
            rememberCoroutineScope() // Get a coroutine scope tied to this composable's lifecycle
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            println("2 inside update App Column $isPlayerLibraryReady")
            Button(onClick = { changeVideoSource(player!!) }) {
                Text("Change Source")
            }

            Divider(Modifier.height(50.dp).fillMaxWidth())

            AnimatedVisibility(isPlayerLibraryReady) {
                println("4 inside update App AnimatedVisibility $isPlayerLibraryReady")
                playVideos(videoElementId, {
                    player = this

                    scope.launch {
                        delay(500) // Delay for 500 milliseconds (adjust as needed)
                        println("JS: Delay finished. Calling updatePlayer.")
                        player?.controls_ = true
                        player?.autoplay(true)
                        player?.preload("auto")
                        player?.src("https://cdn.bitmovin.com/content/assets/sintel/sintel.mpd")

                    }

                    listenErrorEvents(player!!)
                })
            }
        }

        DisposableEffect(LocalLifecycleOwner.current) {
            onDispose {
                println("00 inside update App DisposableEffect onDispose $isPlayerLibraryReady")
                if (null == player) {
                    return@onDispose
                }
                player!!.off()
                if (false == player!!.isDisposed()) {
                    player!!.dispose()
                }
            }
        }
    }
}

@Composable
fun playVideos(videoElementId: String, updatePlayer: Player?.() -> Unit) {
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
            val videoElement = document.createElement("video-js")
            videoElement.id = videoElementId
            videoElement.className = "vjs-big-play-centered"
            videoElement
        },
        update = { videoElement ->
            if ((videoElement.getAttribute("data-vjs-player") == null)) {
                val newVidePlayer = videojs(videoElement, videoOptions)
                updatePlayer(newVidePlayer)
                videoElement.setAttribute("data-vjs-player", "true")
            }
        }
    )

}

@OptIn(ExperimentalWasmJsInterop::class)
fun changeVideoSource(player: Player) {
    println("6 inside update App changeVideoSource")
    val src = createVideoSource(
        videoSrcUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        videoType = "video/mp4"
    )
    player.pause()
    player.poster("https://i.ytimg.com/vi/aqz-KE-bpKQ/maxresdefault.jpg")
    player.src(src)
}

fun listenErrorEvents(player: Player) {
    println("7 inside update App listenErrorEvents")
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

    window.addEventListener("offline", callback = { event ->
        println("'Internet connection lost!'")
        player.pause()
    })

    window.addEventListener("online", callback = { event ->
        println("'Internet connection present!'")
        player.play()
    })
}


fun createVideoOptionsObject(videoSrcUrl: String, videoType: String): VideoJsOptions {
    val source = createVideoSource(videoSrcUrl, videoType)

    val options = VideoJsOptions(controls = true, autoplay = false, preload = "auto",
        sources = arrayOf(source))
    return options
}


fun createVideoSource(videoSrcUrl: String, videoType: String): VideoSource {
    val source = VideoSource(src = videoSrcUrl, type = videoType)
    return source
}