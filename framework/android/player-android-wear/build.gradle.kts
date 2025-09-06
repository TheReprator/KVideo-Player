plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    val packageName = "dev.reprator.video"
    namespace = packageName
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    useLibrary("wear-sdk")
    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
}

kotlin {
    jvmToolchain(24)
}

dependencies {
    implementation(projects.framework.kmpPlayer)

    implementation(platform(libs.compose.bom))
    implementation(libs.ui)

    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)

    implementation(libs.androidx.wear.compose.foundation)
    implementation(libs.androidx.wear.compose.material)

    implementation(libs.android.media3.ui.compose)
    implementation(libs.androidx.wear.horologist.media.ui)
}