package com.github.icarohs7.data.local

import arrow.effects.IO
import java.io.File
import java.io.IOException

class ResourceDao(private val resourcesRootDir: String, private val outputRootDir: String) {
    operator fun <T> IO<T>.unaryPlus(): T = unsafeRunSync()

    fun copy(fileAndResName: String) {
        fileAndResName copyInto fileAndResName
    }

    infix fun String.copyInto(fileName: String) {
        +createFromResource(this, fileName)
    }

    private fun createFromResource(resourceName: String, outputFileName: String): IO<Unit> {
        return IO {
            val resData = this[resourceName] ?: throw IOException("resource $resourceName not found")
            val outFile = File(outputRootDir, outputFileName)

            outFile.parentFile.mkdirs()
            val wasCreated = outFile.createNewFile()
            if (!wasCreated)
                throw IOException("Couldn't create $outputFileName")

            outFile.writeText(resData)
        }
    }

    private operator fun get(resName: String): String? {
        return javaClass.getResourceAsStream("/$resourcesRootDir/$resName").use { inputStream ->
            inputStream?.bufferedReader()?.useLines { lines ->
                buildString {
                    lines.forEach { line ->
                        appendln(line)
                    }
                }
            }
        }
    }

    companion object {
        fun use(resourcesRootDir: String, outputRootDir: String, block: ResourceDao.() -> Unit) {
            val dao = ResourceDao(resourcesRootDir, outputRootDir)
            block(dao)
        }
    }
}