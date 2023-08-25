plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.8.22"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // module
    implementation(project(mapOf("path" to ":domain")))

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // kotlin-serialization
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")

    // dagger
    implementation("javax.inject:javax.inject:1")
    implementation("com.google.dagger:hilt-core:2.47")
    kapt("com.google.dagger:hilt-compiler:2.47")

    // okhttp
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
}
