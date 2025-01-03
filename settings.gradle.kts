pluginManagement {
    repositories {
        google() // Google's repository for Android plugins
        mavenCentral() // Central repository for dependencies
        gradlePluginPortal() // Required for general Gradle plugins

        // Content filtering to optimize Gradle performance
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Ensures all dependencies come from defined repositories
    repositories {
        google() // Android dependencies
        mavenCentral() // Other Java/Kotlin libraries
    }
}

rootProject.name = "Mobile Apps"
include(":app") // Includes the app module
