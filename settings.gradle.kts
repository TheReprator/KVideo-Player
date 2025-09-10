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