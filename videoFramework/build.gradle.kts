@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
}

kotlin {
    listOf(
        wasmJs{
            compilerOptions {
                freeCompilerArgs.add("-Xwasm-attach-js-exception")
            }
        },
        js()
    ).forEach { target ->
        target.outputModuleName = "videoFramework"
        target.browser()
        target.binaries.library()
    }

    jvm("desktop")
    androidTarget()

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }

        webMain.dependencies {
            implementation(npm("video.js", "8.23.4"))
            implementation(libs.kotlin.wrapper.browser)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.desktop.media.vlc)
        }

        androidMain.dependencies {
            implementation(libs.android.media3.exoplayer)
            implementation(libs.android.media3.exoplayer.dash)
            implementation(libs.android.media3.exoplayer.hls)
            implementation(libs.android.media3.exoplayer.smoothstreaming)
            implementation(libs.android.media3.ui)
            implementation(libs.android.media3.ui.compose)
        }
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("shared_compose_compiler")
    metricsDestination = layout.buildDirectory.dir("shared_compose_metric")
    stabilityConfigurationFiles.addAll(
        project.layout.projectDirectory.file("scripts/compose-stability.conf"),
    )
}

android {
    namespace = "dev.reprator.video"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()
}