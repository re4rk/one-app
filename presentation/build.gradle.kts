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

    // glide
    implementation(libs.glide)
    implementation(libs.glide.landscapist)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)
}
