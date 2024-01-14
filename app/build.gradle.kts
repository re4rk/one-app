plugins {
    id("oneApp.android.application")
    id("oneApp.android.application.compose")
    id("oneApp.android.hilt")
}

android {
    namespace = "com.re4rk.app"

    defaultConfig {
        applicationId = "com.re4rk.app"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:shopping"))

    implementation(project(":domain"))

    implementation(project(":data"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)

    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}