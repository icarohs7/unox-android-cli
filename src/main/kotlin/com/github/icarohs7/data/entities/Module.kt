package com.github.icarohs7.data.entities

import arrow.core.Try
import arrow.core.Tuple2
import arrow.core.toTuple2
import com.github.icarohs7.data.local.ResourceDao
import com.github.icarohs7.domain.extensions.recursiveChildrenSequence
import com.github.icarohs7.domain.extensions.replaceCurlyVariables
import java.io.File

/**
 * Represent a child module of a project
 */
class Module(group: String, private val name: String) {
    private val completeName = "$group.$name"
    private val packageDestination = completeName.replace(".", "/")
    private val replaces: List<Tuple2<String, String>> = listOf(
            "module.group" to group,
            "module.name" to name,
            "module.flatName" to name.replace("-",""),
            "module.completeName" to completeName
    ).map { it.toTuple2() }

    fun createAndroidAppOnDisk(): Try<Unit> {
        return Try {
            val rootDir = "androidapp"
            copyBuildscript(rootDir)

            ResourceDao.use("$rootDir/content", "$name/src/main") {
                fixContents(copy("AndroidManifest.xml"))
                fixContents(copyResourceFolderToDirectory("$rootDir/content/res", "/res"))

                val originPackageContents = "$rootDir/content/code"
                fixContents(copyResourceFolderToDirectory(originPackageContents, "kotlin/$packageDestination"))

                File("$name/src/test/kotlin/$packageDestination").mkdirs()
            }
        }
    }

    fun createAndroidLibraryOnDisk(): Try<Unit> {
        return Try {
            val rootDir = "androidlibrary"
            copyBuildscript(rootDir)

            ResourceDao.use("$rootDir/content", "$name/src/main") {
                fixContents(copy("AndroidManifest.xml"))
                File("$name/src/main/kotlin/$packageDestination/data").mkdirs()
                File("$name/src/main/kotlin/$packageDestination/domain").mkdirs()
                File("$name/src/main/kotlin/$packageDestination/presentation").mkdirs()
                File("$name/src/main/res").mkdirs()
                File("$name/src/test/kotlin/$packageDestination").mkdirs()
            }
        }
    }

    fun createJvmJavaFxAppOnDisk(): Try<Unit> {
        return Try {
            copyContentsOfResDir("javafxapp")
        }
    }

    fun createJvmMppLibraryOnDisk():Try<Unit> {
        return Try {
            copyContentsOfResDir("javampplibrary")
        }
    }

    private fun copyContentsOfResDir(rootDir: String) {
        copyBuildscript(rootDir)

        ResourceDao.use("$rootDir/content", "$name/src/main") {
            fixContents(copyResourceFolderToDirectory("$rootDir/resources", "/resources"))

            val originPackageContents = "$rootDir/content/code"
            fixContents(copyResourceFolderToDirectory(originPackageContents, "kotlin/$packageDestination"))

            File("$name/src/main/kotlin/$packageDestination/data").mkdirs()
            File("$name/src/main/kotlin/$packageDestination/domain").mkdirs()
            File("$name/src/main/kotlin/$packageDestination/presentation").mkdirs()
            File("$name/src/test/kotlin/$packageDestination").mkdirs()
        }
    }

    private fun copyBuildscript(rootDir: String) {
        ResourceDao.use(rootDir, name) {
            fixContents(copy("build.gradle.kts"))
        }
    }

    private fun fixContents(file: File) {
        if (file.isFile)
            file.replaceCurlyVariables(replaces)
        else {
            file.recursiveChildrenSequence().forEach {
                if (it.isFile)
                    it.replaceCurlyVariables(replaces)
            }
        }
    }
}