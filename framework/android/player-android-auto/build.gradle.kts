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

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    useLibrary("android.car")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(projects.framework.kmpPlayer)

    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.bom.material3)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)

    implementation(libs.android.media3.ui.compose)
}