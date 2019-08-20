package com.github.icarohs7.data.entities

import arrow.core.Try
import com.github.icarohs7.data.local.ResourceDao

/**
 * A root level gradle module
 */
class Project(private val name: String) {

    /**
     * Create the project files and
     * save the on disk
     */
    fun createOnDisk(): Try<Unit> {
        return Try {
            ResourceDao.use("rootmodule", name) {
                copyResourceFolderToDirectory("rootmodule")
            }
        }
    }
}