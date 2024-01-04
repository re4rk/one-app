plugins {
    id("oneApp.android.feature")
    id("oneApp.android.library.compose")
}

android {
    namespace = "com.re4rk.presentation"

    android.sourceSets.all {
        kotlin.srcDir("src/$name/kotlin")
    }
}

dependencies {
    // android
    implementation(libs.androidx.core.ktx)
    implementation(libs.android.material)

    // glide
    implementation(libs.glide)
    implementation(libs.glide.landscapist)

    // compose
    implementation(libs.androidx.compose.ui.graphic)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)
}
