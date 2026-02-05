package com.artiuillab.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
        val composeCompilerVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        val composeBom = libs.findLibrary("androidx-compose-bom").get()

        target.pluginManager.withPlugin("com.android.application") {
            target.extensions.getByType<ApplicationExtension>().apply {
                buildFeatures.compose = true
                composeOptions.kotlinCompilerExtensionVersion = composeCompilerVersion
            }
            target.dependencies {
                add("implementation", platform(composeBom))
                add("androidTestImplementation", platform(composeBom))
            }
        }

        target.pluginManager.withPlugin("com.android.library") {
            target.extensions.getByType<LibraryExtension>().apply {
                buildFeatures.compose = true
                composeOptions.kotlinCompilerExtensionVersion = composeCompilerVersion
            }
            target.dependencies {
                add("implementation", platform(composeBom))
                add("androidTestImplementation", platform(composeBom))
            }
        }
    }
}