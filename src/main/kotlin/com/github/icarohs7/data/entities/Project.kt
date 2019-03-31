package com.github.icarohs7.data.entities

import arrow.effects.IO
import com.github.icarohs7.data.local.ResourceDao

class Project(private val name: String) {
    fun createOnDisk(): IO<Unit> {
        return IO {
            ResourceDao.use("rootmodule", name) {
                "gitignore" copyInto ".gitignore"
                copy("build.gradle.kts")
                copy("settings.gradle.kts")
                copy("gradle.properties")
                copy(".travis.yml")
                copy(".gitlab-ci.yml")
            }
        }
    }
}