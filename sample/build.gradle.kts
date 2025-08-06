@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.js-plain-objects") version libs.versions.kotlin
}

kotlin {
    val rootDirPath = project.rootDir.path
    val projectDirPath = project.projectDir.path

    wasmJs {
        compilerOptions {
            freeCompilerArgs.add("-Xwasm-attach-js-exception")
            freeCompilerArgs.add("-Xwasm-use-new-exception-proposal")
        }

        generateTypeScriptDefinitions()
        outputModuleName = "sample"
        browser {

            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "sample.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                    sourceMaps = true
                    port = 3000
                }
            }
        }
        binaries.executable()
    }

    js {
        useEsModules()
        outputModuleName = "samplejs"
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "samplejs.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                    sourceMaps = true
                    port = 3001
                }
            }
        }
        binaries.executable()
    }

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }

        webMain.dependencies {
            implementation(npm("video.js", "8.6.1"))
            implementation(project(":htmlInterop"))
        }
    }
}