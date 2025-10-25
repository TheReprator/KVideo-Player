@file:OptIn(ExperimentalWasmDsl::class, ExperimentalKotlinGradlePluginApi::class)

import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

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

    val xcFrameworkName = "VideoFrameWorkKMP"
    val xcf = XCFramework(xcFrameworkName)
    listOf(
        tvosArm64(),
        tvosSimulatorArm64(),
        tvosX64(),

        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = xcFrameworkName
            binaryOption("bundleId", "dev.reprator.${xcFrameworkName}")
            xcf.add(this)
            isStatic = true
        }
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