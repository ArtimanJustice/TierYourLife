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
    namespace = "com.artiuillab.features.signin"
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("reports")
}

dependencies {
    implementation(project(":features:signin:domain"))
    implementation(project(":core:essentials"))
    implementation(project(":core:theme"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}