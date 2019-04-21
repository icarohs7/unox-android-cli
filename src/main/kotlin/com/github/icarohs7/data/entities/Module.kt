package com.github.icarohs7.data.entities

import arrow.core.Tuple2
import arrow.core.toTuple2
import arrow.effects.IO
import com.github.icarohs7.data.local.ResourceDao
import com.github.icarohs7.domain.extensions.recursiveChildrenSequence
import com.github.icarohs7.domain.extensions.replaceCurlyVariables
import java.io.File

/**
 * Represent a child module of a project
 */
class Module(group: String, private val name: String) {
    val completeName = "$group.$name"
    private val replaces: List<Tuple2<String, String>> = listOf(
            "module.group" to group,
            "module.name" to name,
            "module.completeName" to completeName
    ).map { it.toTuple2() }

    /**
     * Create the project files and
     * save the on disk
     */
    fun createStandaloneOnDisk(): IO<Unit> {
        return IO {
            ResourceDao.use("childmodule/standalone", name) {
                fixContents(copy("build.gradle.kts"))
            }

            ResourceDao.use("childmodule/standalone/content", "$name/main") {
                fixContents(copy("AndroidManifest.xml"))
                fixContents(copyResourceFolderToDirectory("childmodule/standalone/content/res", "/res"))

                val originPackageContents = "childmodule/standalone/content/code"
                val packageDestination = completeName.replace(".", "/")
                fixContents(copyResourceFolderToDirectory(originPackageContents, packageDestination))
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