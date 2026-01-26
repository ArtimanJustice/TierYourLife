plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.convention.kotlin)
}

dependencies {
    implementation(libs.javax.inject)
    testImplementation(libs.junit)
}