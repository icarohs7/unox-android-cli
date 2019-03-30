package com.github.icarohs7.data.templates.gradle

class RootBuild : Build {
    override val fileContent: String = buildString {
        append("""
            plugins {
                defaults.`root-module`
                id("com.github.ben-manes.versions") version "0.21.0"
            }
        """.trimIndent())
    }
}