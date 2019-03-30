package com.github.icarohs7.data.templates.gradle

import com.github.icarohs7.data.templates.FileModel
import com.github.icarohs7.domain.extensions.include

class Settings : FileModel {
    override val fileName: String = "settings"
    override val fileExtension: String = "gradle.kts"

    val includedModules: MutableList<String> = mutableListOf()
    override val fileContent: String
        get() = buildString {
            include(includedModules)

            append("""
                pluginManagement {
                    resolutionStrategy {
                        eachPlugin {
                            if (requested.id.id.startsWith("org.jetbrains.kotlin."))
                                useVersion(Versions.kotlin)
                        }
                    }
                }
            """.trimIndent())
        }
}