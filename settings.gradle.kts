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
include(":framework:player-android-wear")
include(":framework:player-android-tv")
include(":framework:player-android-auto")
include(":sample")
include(":sample:android-wear")
include(":sample:android-tv")
include(":sample:android-automotive")