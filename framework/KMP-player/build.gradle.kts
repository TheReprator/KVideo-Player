@file:OptIn(ExperimentalWasmDsl::class, ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl


plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {

    listOf(
        wasmJs {
            compilerOptions {
                freeCompilerArgs.add("-Xwasm-attach-js-exception")
            }
        },
        js()
    ).forEach { target ->
        target.outputModuleName = "kmpVideoFramework"
        target.generateTypeScriptDefinitions()
        target.browser()
        target.binaries.library()
    }

    jvm("desktop")
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.androidx.compose.runtime)
            implementation(libs.kotlinx.coroutines)
        }

        webMain.dependencies {
            api(npm("video.js", "8.23.4"))
            api(libs.web.kotlin.wrapper.browser)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            api(libs.desktop.media.vlc)
        }

        androidMain.dependencies {
            api(libs.android.media3.exoplayer)
            implementation(libs.android.media3.exoplayer.dash)
            implementation(libs.android.media3.exoplayer.hls)
            implementation(libs.android.media3.exoplayer.smoothstreaming)
        }

        appleMain.dependencies {
            implementation(libs.ios.xml)
        }
    }
}

android {
    namespace = "dev.reprator.kmp.video"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()
}