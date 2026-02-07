package com.artiuillab.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class DomainConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins.apply("org.jetbrains.kotlin.jvm")

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            add("compileOnly", libs.findLibrary("compose.stable.marker").get())
        }
    }
}