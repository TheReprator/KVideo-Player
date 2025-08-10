@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.reload.gradle.ComposeHotRun
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.hotReload)
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

    jvm("desktop")

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

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.desktop.media.vlc)
        }
    }
}

private val desktopPackageName = "dev.reprator.videos.demo"

tasks.withType<ComposeHotRun>().configureEach {
    mainClass = "$desktopPackageName.MainKt"
}

compose.desktop {
    application {
        mainClass = "$desktopPackageName.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = desktopPackageName
            packageVersion = "1.0.0"
            includeAllModules = true

            linux {
                iconFile.set(project.file("resources/LinuxIcon.png"))
            }
            windows {
                iconFile.set(project.file("resources/WindowsIcon.ico"))
            }
            macOS {
                iconFile.set(project.file("resources/MacosIcon.icns"))
                bundleID = desktopPackageName
            }
        }

        buildTypes.release.proguard {
            configurationFiles.from("compose-desktop.pro")
        }
    }
}