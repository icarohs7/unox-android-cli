package com.github.icarohs7.data.templates.gradle

import com.github.icarohs7.data.templates.FileModel

interface Build : FileModel {
    override val fileName: String get() = "build"
    override val fileExtension: String get() = "gradle.kts"
}