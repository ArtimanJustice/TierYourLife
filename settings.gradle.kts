@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TierYourLife"
include(":app")
include(":features:init:presentation")
include(":features:init:domain")
include(":features:signin:presentation")
include(":features:signin:domain")
include(":core:android")
include(":core:essentials")
include(":core:navigation-dsl")
include(":navigation")
include(":core:theme")
