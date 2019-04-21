package com.github.icarohs7.data.entities

import arrow.effects.IO
import com.github.icarohs7.data.local.ResourceDao
import org.eclipse.jgit.api.Git
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
                copyResourceFolderToDirectory("rootmodule")
                cloneBuildSrc().unsafeRunSync()
                cloneCoreLibrary().unsafeRunSync()
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
        }
    }

    /**
     * Clone the corelibrary folder from the repository
     * [unox-android-corelibrary](https://github.com/icarohs7/unox-android-corelibrary)
     */
    private fun cloneCoreLibrary(): IO<Unit> {
        return IO {
            val out = File(name, "corelibrary")
            out.mkdirs()

            Git.cloneRepository()
                    .setURI("https://github.com/icarohs7/unox-android-corelibrary")
                    .setDirectory(out)
                    .call()
                    .close()
        }
    }
}