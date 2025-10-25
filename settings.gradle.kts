rootProject.name = "KVideo-Player"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    //https://github.com/JetBrains/compose-hot-reload?tab=readme-ov-file#set-up-automatic-provisioning-of-the-jetbrains-runtime-jbr-via-gradle
    id("org.gradle.toolchains.foojay-resolver-convention").version("1.0.0")
}


include(":framework:KMP-player")
include(":framework:player-ui")
include(":framework:android:player-android-wear")
include(":framework:android:player-android-tv")
include(":framework:android:player-android-auto")
include(":sample")
include(":sample:android:android-wear")
include(":sample:android:android-tv")
include(":sample:android:android-automotive")
include(":sample:android:android-xr")