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
class AndroidModule(group: String, private val name: String) {
    private val completeName = "$group.$name"
    private val packageDestination = completeName.replace(".", "/")
    private val replaces: List<Tuple2<String, String>> = listOf(
            "module.group" to group,
            "module.name" to name,
            "module.completeName" to completeName
    ).map { it.toTuple2() }

    /**
     * Create the project files and
     * save the on disk
     */
    fun createStandaloneOnDisk(): Try<Unit> {
        return Try {
            ResourceDao.use("childmodule/standalone", name) {
                fixContents(copy("build.gradle.kts"))
            }

            ResourceDao.use("childmodule/standalone/content", "$name/src/main") {
                fixContents(copy("AndroidManifest.xml"))
                fixContents(copyResourceFolderToDirectory("childmodule/standalone/content/res", "/res"))

                val originPackageContents = "childmodule/standalone/content/code"
                fixContents(copyResourceFolderToDirectory(originPackageContents, "kotlin/$packageDestination"))

                File("$name/src/test/kotlin/$packageDestination").mkdirs()
            }
        }
    }

    fun createLibraryOnDisk(): Try<Unit> {
        return Try {
            ResourceDao.use("childmodule/library", name) {
                fixContents(copy("build.gradle.kts"))
            }

            ResourceDao.use("childmodule/library/content", "$name/src/main") {
                fixContents(copy("AndroidManifest.xml"))
                File("$name/src/main/kotlin/$packageDestination/data").mkdirs()
                File("$name/src/main/kotlin/$packageDestination/domain").mkdirs()
                File("$name/src/main/kotlin/$packageDestination/presentation").mkdirs()
                File("$name/src/main/res").mkdirs()
                File("$name/src/test/kotlin/$packageDestination").mkdirs()
            }
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