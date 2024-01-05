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
    implementation(project(":core:designsystem"))

    // android
    implementation(libs.androidx.core.ktx)
    implementation(libs.android.material)

    // glide
    implementation(libs.glide)
    implementation(libs.glide.landscapist)

    // compose
    implementation(libs.androidx.compose.ui.graphic)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)
}
