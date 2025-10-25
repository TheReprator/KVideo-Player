@file:OptIn(ExperimentalWasmDsl::class, ExperimentalKotlinGradlePluginApi::class)

import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {

    jvmToolchain(21)

    android {
        namespace = "dev.reprator.kmp.video"
        minSdk = libs.versions.android.minSdk.get().toInt()
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        androidResources.enable = true
    }

    listOf(
        wasmJs {
            compilerOptions {
                freeCompilerArgs.add("-Xwasm-attach-js-exception")
            }
        },
        js()
    ).forEach { target ->
        target.generateTypeScriptDefinitions()
        target.browser()
        target.binaries.library()
    }

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    targets
        .withType<KotlinNativeTarget>()
        .matching { it.konanTarget.family.isAppleFamily }
        .configureEach {
            binaries { framework { baseName = "VideoFrameWorkKMP" } }
        }

    sourceSets {
        dependencies {
            implementation(libs.androidx.compose.runtime)
            implementation(libs.kotlinx.coroutines)
        }

        webMain.dependencies {
            implementation(npm("video.js", "8.23.4"))
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