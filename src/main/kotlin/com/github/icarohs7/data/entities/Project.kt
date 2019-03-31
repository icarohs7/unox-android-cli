package com.github.icarohs7.data.entities

import arrow.effects.IO
import com.github.icarohs7.data.local.ResourceDao
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.util.FileUtils
import java.io.File

/**
 * A root level gradle module
 */
class Project(private val name: String) {
    /**
     * Create the project files and
     * save the on disk
     */
    fun createOnDisk(): IO<Unit> {
        return IO {
            ResourceDao.use("rootmodule", name) {
                "gitignore" copyInto ".gitignore"
                copy("build.gradle.kts")
                copy("settings.gradle.kts")
                copy("gradle.properties")
                copy(".travis.yml")
                copy(".gitlab-ci.yml")
                cloneBuildSrc().unsafeRunSync()
            }
        }
    }

    /**
     * Clone the buildSrc folder from the repository
     * [unox-buildsrc](https://github.com/icarohs7/unox-buildsrc)
     */
    private fun cloneBuildSrc(): IO<Unit> {
        return IO {
            val out = File(name, "buildSrc")
            out.mkdirs()

            Git.cloneRepository()
                    .setURI("https://github.com/icarohs7/unox-buildsrc")
                    .setDirectory(out)
                    .call()
                    .close()

            deleteUnnecessaryClonedFiles()
        }
    }

    /**
     * Used to remove some unnecessary files from
     * the cloned repository, like LICENCE and
     * README.MD
     */
    private fun deleteUnnecessaryClonedFiles() {
        val rootDir = File(name, "buildSrc")
        val delete = { name: String -> File(rootDir, name).delete() }

        delete("LICENSE")
        delete("README.md")
        delete(".gitignore")
        delete("push-updates.py")
        FileUtils.delete(File(rootDir, ".git"), FileUtils.RECURSIVE)
    }
}