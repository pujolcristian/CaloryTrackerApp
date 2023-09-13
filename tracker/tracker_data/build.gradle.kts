plugins {
    `android-library`
    `kotlin-android`
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.cpujoldev.tracker_data"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain))

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    "kapt"(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)
}
