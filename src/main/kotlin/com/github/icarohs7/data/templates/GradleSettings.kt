package com.github.icarohs7.data.templates

class GradleSettings : FileModel {
    override val fileName: String = "settings"
    override val fileExtension: String = "gradle.kts"
    override val fileContent: String get() = makeContent()

    private fun makeContent(): String = """
        pluginManagement {
            resolutionStrategy {
                eachPlugin {
                    if (requested.id.id.startsWith("org.jetbrains.kotlin."))
                        useVersion(Versions.kotlin)
                }
            }
        }
    """.trimIndent()
}