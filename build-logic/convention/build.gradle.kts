plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}
repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}
gradlePlugin {
    plugins {
        create("kotlinConvention") {
            id = "com.artiuillab.convention.kotlin"
            implementationClass = "com.artiuillab.buildlogic.KotlinConventionPlugin"
        }
        create("androidBaseConvention") {
            id = "com.artiuillab.convention.android"
            implementationClass = "com.artiuillab.buildlogic.AndroidBaseConventionPlugin"
        }
        create("composeConvention") {
            id = "com.artiuillab.convention.compose"
            implementationClass = "com.artiuillab.buildlogic.ComposeConventionPlugin"
        }
        create("androidBuildTypesConvention") {
            id = "com.artiuillab.convention.android.buildtypes"
            implementationClass = "com.artiuillab.buildlogic.AndroidBuildTypesConventionPlugin"
        }
        create("domainConvention") {
            id = "com.artiuillab.convention.domain"
            implementationClass = "com.artiuillab.buildlogic.DomainConventionPlugin"
        }
    }
}
