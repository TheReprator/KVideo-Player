@file:OptIn(ExperimentalWasmDsl::class, ExperimentalDistributionDsl::class)

import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalDistributionDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import java.util.Locale

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.hot.reload)
    alias(libs.plugins.android.application)
}

kotlin {
    jvmToolchain(21)

    listOf(
        wasmJs {
            compilerOptions {
                freeCompilerArgs.add("-Xwasm-use-new-exception-proposal")
            }
        },
        js()
    ).forEach { target ->
        target.browser {

            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path

            distribution {
                outputDirectory = File("$rootDirPath/dist/${project.name}")
            }

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

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm("desktop")
    androidTarget()

    targets
        .withType<KotlinNativeTarget>()
        .matching { it.konanTarget.family.isAppleFamily }
        .configureEach {
            binaries {
                framework {
                    baseName = "VideoFrameWorkSample"

                    //binaryOption("smallBinary", "true")
                    //binaryOption("bundleId", "dev.reprator.video.demo")
                }
            }
        }

    sourceSets {

        commonMain.dependencies {
            implementation(projects.framework.kmpPlayer)
            implementation(projects.framework.playerUi)
            implementation(compose.ui)
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("andoridApp_compose_compiler")
    metricsDestination = layout.buildDirectory.dir("androidApp_compose_metric")
    stabilityConfigurationFiles.addAll(
        project.layout.projectDirectory.file("../scripts/compose-stability.conf"),
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

val extractedResourcesDir = layout.buildDirectory.dir("extractedVideoFrameworkResources")

val copyVideoFrameworkResources by tasks.registering(Copy::class) {
    val frameworkJarProvider =
        project(":framework:KMP-player").tasks.named("desktopJar", Jar::class.java)
            .flatMap { it.archiveFile }
    from(zipTree(frameworkJarProvider)) {
        val osName = System.getProperty("os.name", "").lowercase(Locale.getDefault())
        val osArch = System.getProperty("os.arch", "").lowercase(Locale.getDefault())
        val includePath = when {
            osName.contains("mac") && (osArch == "aarch64" || osArch == "arm64") -> "macos-arm64"
            osName.contains("mac") && (osArch == "x86_64" || osArch == "amd64") -> "macos-x86_64"
            osName.contains("win") && (osArch == "amd64" || osArch == "x86_64") -> "windows-x64"
            osName.contains("linux") && (osArch == "amd64" || osArch == "x86_64") -> "linux-x64"
            else -> {
                return@from
            }
        }
        include("appResources/$includePath/**")
    }
    into(extractedResourcesDir)
}

tasks.withType<JavaExec> {
    if (name == "run" || name == "hotRunDesktop") {
        dependsOn(copyVideoFrameworkResources)

        val nativesPath = extractedResourcesDir.get().asFile.absolutePath
        systemProperty("java.library.path", nativesPath)
        systemProperty("vlcj.log", "DEBUG")

        systemProperty("compose.application.resources.dir", file("appResources").absolutePath)
    }
}