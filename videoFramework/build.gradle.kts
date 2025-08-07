@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
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

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }

        webMain.dependencies {
            implementation(npm("video.js", "8.6.1"))
            implementation(libs.kotlin.wrapper.browser)
        }
    }
}