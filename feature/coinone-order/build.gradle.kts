plugins {
    id("oneApp.android.feature")
    id("oneApp.android.library.compose")
}

android {
    namespace = "com.re4rk.oneapp.feature.coinoneorder"

    android.sourceSets.all {
        kotlin.srcDir("src/$name/kotlin")
    }
}

dependencies {

    implementation(project(":core:data-coinone"))
    implementation(project(":core:model-coinone"))
    implementation(project(":core:designsystem"))

    // glide
    implementation(libs.glide)
    implementation(libs.glide.landscapist)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)
}
