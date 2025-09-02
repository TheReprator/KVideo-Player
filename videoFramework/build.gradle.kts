@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.kotlin.multiplatform.library)
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

    androidLibrary {
        namespace = "dev.reprator.video"
        compileSdk =
            libs.versions.android.compileSdk
                .get()
                .toInt()
        minSdk= libs.versions.android.minSdk
            .get()
            .toInt()

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

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
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.kotlinx.coroutines)
        }

        webMain.dependencies {
            implementation(npm("video.js", "8.23.4"))
            implementation(libs.kotlin.wrapper.browser)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.desktop.media.vlc)
            implementation(libs.kotlinx.coroutines.swing)
        }

        androidMain.dependencies {
            implementation(libs.android.media3.exoplayer)
            implementation(libs.android.media3.exoplayer.dash)
            implementation(libs.android.media3.exoplayer.hls)
            implementation(libs.android.media3.exoplayer.smoothstreaming)
            implementation(libs.android.media3.ui)
            implementation(libs.android.media3.ui.compose)
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.junit)
            }
        }

        appleMain.dependencies {
            implementation(libs.ios.xml)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
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

kotlin.sourceSets.all {
    languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}