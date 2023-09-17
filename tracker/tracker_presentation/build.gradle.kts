plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.cpujoldev.tracker_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain))

    implementation(Coil.coilCompose)
}
