package com.example.myapplication

import androidx.compose.ui.window.ComposeUIViewController
import dev.reprator.video.demo.VideoScreen
import dev.reprator.video.platform.impl.PlaybackStateControllerImplIos

fun MainViewController() = ComposeUIViewController {
    VideoScreen(PlaybackStateControllerImplIos())
}