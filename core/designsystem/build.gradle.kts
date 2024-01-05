plugins {
    id("oneApp.android.library")
    id("oneApp.android.library.compose")
}

android {
    namespace = "com.re4rk.oneapp.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)
}
