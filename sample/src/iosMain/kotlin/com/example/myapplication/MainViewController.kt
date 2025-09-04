package com.example.myapplication

import androidx.compose.ui.window.ComposeUIViewController
import dev.reprator.video.demo.VideoScreen
import dev.reprator.kmp.video.platform.impl.PlaybackStateControllerImplIos

fun MainViewController() = ComposeUIViewController {
    VideoScreen(PlaybackStateControllerImplIos())
}