@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    val rootDirPath = project.rootDir.path
    val projectDirPath = project.projectDir.path

    listOf(
        wasmJs{
            compilerOptions {
                freeCompilerArgs.add("-Xwasm-use-new-exception-proposal")
            }
        },
        js()
    ).forEach { target ->
        target.browser {

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

        target.generateTypeScriptDefinitions()
        target.outputModuleName = "sample"
        target.useEsModules()
        target.binaries.executable()
    }

    sourceSets {

        commonMain.dependencies {
            implementation(projects.videoFramework)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }

        webMain.dependencies {
            implementation(libs.kotlin.wrapper.browser)
        }
    }
}