package dev.reprator.video.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.material3.MaterialTheme
import androidx.media3.common.util.UnstableApi
import dev.reprator.kmp.video.platform.impl.PlaybackStateControllerImplAndroid

@OptIn(UnstableApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                VideoScreen(PlaybackStateControllerImplAndroid(this))
            }
        }
    }
}