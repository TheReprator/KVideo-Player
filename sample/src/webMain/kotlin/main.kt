import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import ui.AppVideoPlayer

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    ComposeViewport("composeApplication") {
        AppVideoPlayer()
    }
}
