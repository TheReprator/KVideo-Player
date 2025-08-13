package dev.reprator.video.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.MaterialTheme
import dev.reprator.video.platform.impl.PlaybackStateControllerImplAndroid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                VideoScreen(PlaybackStateControllerImplAndroid(this))
            }
        }
    }
}
