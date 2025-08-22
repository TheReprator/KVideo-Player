@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

import java.util.Locale

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
            baseName = "VideoFrameWork"
        }
    }

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
            implementation("org.mp4parser:isoparser:1.9.56")
            implementation("io.github.pdvrieze.xmlutil:serialization:0.91.2")

            val osName = System.getProperty("os.name", "").lowercase(Locale.getDefault())
            val osArch = System.getProperty("os.arch", "").lowercase(Locale.getDefault())
            val fxSuffix = when {
                osName.contains("linux") && osArch == "x86_64" -> "linux"
                osName.contains("linux") && osArch == "aarch64" -> "linux-aarch64"
                osName.contains("windows") && osArch == "amd64" -> "win"
                osName.contains("mac") && osArch == "x86_64" -> "mac"
                osName.contains("mac") && (osArch == "aarch64" || osArch == "arm64") -> "mac-aarch64"
                else -> throw IllegalStateException("Unknown OS/Arch: $osName / $osArch")
            }

            implementation("org.openjfx:javafx-base:24.0.2:${fxSuffix}")
            implementation("org.openjfx:javafx-graphics:24.0.2:${fxSuffix}")
            implementation("org.openjfx:javafx-controls:24.0.2:${fxSuffix}")
            implementation("org.openjfx:javafx-swing:24.0.2:${fxSuffix}")
            implementation("org.openjfx:javafx-media:24.0.2:${fxSuffix}")
        }

        androidMain.dependencies {
            implementation(libs.android.media3.exoplayer)
            implementation(libs.android.media3.exoplayer.dash)
            implementation(libs.android.media3.exoplayer.hls)
            implementation(libs.android.media3.exoplayer.smoothstreaming)
            implementation(libs.android.media3.ui)
            implementation(libs.android.media3.ui.compose)
        }

        appleMain.dependencies {
            implementation("io.github.pdvrieze.xmlutil:serialization:0.91.2")
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