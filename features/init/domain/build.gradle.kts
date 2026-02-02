plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.convention.kotlin)
    alias(libs.plugins.convention.domain)
}

dependencies {
    implementation(project(":core:essentials"))
    compileOnly(libs.compose.stable.marker)
}