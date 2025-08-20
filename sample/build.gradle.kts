@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.hotReload)
    alias(libs.plugins.android.application)
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
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            binaryOption("smallBinary", "true")
            binaryOption("bundleId", "dev.reprator.video.demo")
            isStatic = true
            baseName = "VideoFrameWorkSample"
        }
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

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
    }
}

android {
    val packageName = "dev.reprator.video.demo"
    namespace = packageName
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = packageName
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("andoridApp_compose_compiler")
    metricsDestination = layout.buildDirectory.dir("androidApp_compose_metric")
    stabilityConfigurationFiles.addAll(
        project.layout.projectDirectory.file("scripts/compose-stability.conf"),
    )
}

compose.desktop {
    val desktopPackageName = "dev.reprator.video.demo"

    application {
        mainClass = "$desktopPackageName.MainKt"

        nativeDistributions {

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = desktopPackageName
            packageVersion = "1.0.0"
            includeAllModules = true

            macOS {
                bundleID = desktopPackageName
            }
        }

        buildTypes.release.proguard {
            configurationFiles.from("compose-desktop.pro")
        }
    }
}