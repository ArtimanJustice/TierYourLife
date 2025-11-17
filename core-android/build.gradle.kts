plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.convention.android)
    alias(libs.plugins.convention.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.artiuil.lab.android"
}

dependencies {
    api(project(":core-essentials"))

    implementation(libs.timber)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}