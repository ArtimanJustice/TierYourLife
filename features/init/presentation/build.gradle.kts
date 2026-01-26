plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.convention.android)
    alias(libs.plugins.convention.kotlin)
    alias(libs.plugins.convention.compose)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}


android {
    namespace = "com.artiuillab.features.init.presentation"
}

dependencies {
    implementation(project(":features:init:domain"))
    implementation(project(":core:essentials"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}