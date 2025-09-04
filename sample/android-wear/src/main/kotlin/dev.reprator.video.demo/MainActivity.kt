package dev.reprator.video.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material3.MaterialTheme
import dev.reprator.kmp.video.platform.impl.PlaybackStateControllerImplAndroid

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