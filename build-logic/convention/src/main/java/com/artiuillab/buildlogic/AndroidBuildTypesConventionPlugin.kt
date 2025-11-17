package com.artiuillab.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidBuildTypesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.withPlugin("com.android.application") {
            target.extensions.configure<ApplicationExtension> {
                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                    }
                }
            }
        }
    }
}