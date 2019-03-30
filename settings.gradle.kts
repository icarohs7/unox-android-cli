rootProject.name = "unox-android-cli"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin."))
                useVersion(Versions.kotlin)
        }
    }
}