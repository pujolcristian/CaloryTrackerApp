plugins {
    `android-library`
    `kotlin-android`
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.cpujoldev.onboarding_domain"
}
