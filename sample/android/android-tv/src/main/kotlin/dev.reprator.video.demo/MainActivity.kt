package dev.reprator.video.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.media3.common.util.UnstableApi
import androidx.tv.material3.Surface
import dev.reprator.kmp.video.platform.impl.PlaybackStateControllerImplAndroid
import dev.reprator.video.demo.theme.MyApplicationTVTheme

@OptIn(UnstableApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    VideoScreen(PlaybackStateControllerImplAndroid(this@MainActivity))
                }
            }
        }
    }
}