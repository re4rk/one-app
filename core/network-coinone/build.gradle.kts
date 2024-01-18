plugins {
    id("oneApp.kotlin.library")
    kotlin("plugin.serialization") version "1.8.22"
}
dependencies {
    implementation(project(":core:model-coinone"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
}
