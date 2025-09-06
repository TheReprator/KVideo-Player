@file:OptIn(ExperimentalWasmDsl::class, ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl


plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
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
        target.outputModuleName = "videoFramework"
        target.browser()
        target.generateTypeScriptDefinitions()
        target.binaries.library()
    }

    jvm("desktop")
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
            baseName = "VideoFrameWork"
        }
    }

    sourceSets {

        commonMain.dependencies {
            implementation(projects.framework.kmpPlayer)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.kotlinx.coroutines)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }

        androidMain.dependencies {
            implementation(libs.android.media3.ui)
            implementation(libs.android.media3.ui.compose)
        }
    }
}
composeCompiler {
    reportsDestination = layout.buildDirectory.dir("shared_compose_compiler")
    metricsDestination = layout.buildDirectory.dir("shared_compose_metric")
    stabilityConfigurationFiles.addAll(
        project.layout.projectDirectory.file("../scripts/compose-stability.conf"),
    )
}

android {
    namespace = "dev.reprator.video"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()
}