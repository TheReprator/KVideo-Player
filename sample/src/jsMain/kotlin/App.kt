import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hamama.kwhi.HtmlView
import kotlinx.dom.appendElement


@Composable
fun App() {
    var videoUrl by remember { mutableStateOf("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") }
    val videoId = "dynamic-video-js"

    MaterialTheme {
        Text("Vikram Singh outer ")
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Vikram Singh inner")
            HtmlView(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                factory = {
                    val video = createElement("video")
                    video.setAttribute("id",videoId)
                    video.setAttribute("class", "video-js vjs-default-skin")
                    video.setAttribute("controls","")
                    video.setAttribute("preload","auto")
                    video.setAttribute("data-setup","{}")
                    video.appendElement("source") {
                        setAttribute("src",videoUrl)
                        setAttribute("type","video/mp4")
                    }
                    video
                }
            )
        }
    }

}