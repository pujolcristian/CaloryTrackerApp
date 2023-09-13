plugins {
    `android-library`
    `kotlin-android`
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.cpujoldev.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.onboardingDomain))
}
