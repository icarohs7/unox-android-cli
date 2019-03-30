package com.github.icarohs7.data.templates

import arrow.effects.IO
import java.nio.file.Files
import java.nio.file.Paths

interface FileModel {
    val fileName: String
    val fileExtension: String
    val fileContent: String

    val completeFileName get() = "$fileName.$fileExtension"

    fun write(directory: String): IO<Unit> {
        val dir = Paths.get(directory)
        val file = dir.resolve(completeFileName).toFile()


        return IO {
            if (!Files.exists(dir))
                Files.createDirectories(dir)

            if (!file.exists())
                if (!file.createNewFile())
                    throw IllegalStateException("Couldn't create the file ${file.name}")

            file.writeText(fileContent)
        }
    }
}