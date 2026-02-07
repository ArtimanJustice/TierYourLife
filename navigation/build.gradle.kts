plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.convention.android)
    alias(libs.plugins.convention.kotlin)

    alias(libs.plugins.convention.compose)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.artiuillab.navigation"
}

ksp {
    arg("effects.processor.metadata", "generate")
}

dependencies {
    implementation(project(":features:init:presentation"))
    implementation(project(":features:signin:presentation"))
    implementation(project(":core:navigation-dsl"))

    implementation(libs.timber)

    implementation(libs.effects.hilt.compose)
    ksp(libs.effects.hilt.compiler)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}