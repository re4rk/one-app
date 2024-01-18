plugins {
    id("oneApp.kotlin.library")
}

dependencies {
    implementation(project(":core:model-coinone"))
    implementation(project(":core:network-coinone"))

    implementation(libs.retrofit.core)
}
