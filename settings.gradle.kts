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
include(":sample")
include(":sample:android-wear")