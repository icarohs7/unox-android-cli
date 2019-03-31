include(":corelibrary")
//include(":authresources")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin."))
                useVersion(Versions.kotlin)
        }
        repositories {
            google()
            jcenter()
            gradlePluginPortal()
        }
    }
}
