plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.compose.hotReload)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
}