package com.github.icarohs7.data.local

import arrow.effects.IO
import java.io.File
import java.io.IOException
import java.net.URLDecoder
import java.util.jar.JarFile


/**
 * Dao responsible of handling the resources and file
 * operations of the application
 */
class ResourceDao(private val resourcesRootDir: String, private val outputRootDir: String) {

    /**
     * Copy the resource with the given [fileAndResName]
     * to a file with the same name in the [outputRootDir]
     */
    fun copy(fileAndResName: String) {
        fileAndResName copyInto fileAndResName
    }

    /**
     * Copy the resource with name got from the receiver String
     * to a file named after the [fileName] parameter
     * in the [outputRootDir]
     */
    infix fun String.copyInto(fileName: String) {
        createFromResource(this, fileName).unsafeRunSync()
    }

    /**
     * Copy the contents of the given resource to an
     * output file in the [outputRootDir]
     */
    private fun createFromResource(resourceName: String, outputFileName: String): IO<Unit> {
        return IO {
            val resData = get(resourceName) ?: throw IOException("resource $resourceName not found")
            val outFile = File(outputRootDir, outputFileName)

            outFile.parentFile.mkdirs()

            val wasCreated = outFile.createNewFile()
            if (!wasCreated)
                throw IOException("Couldn't create $outputFileName")

            outFile.writeText(resData)
        }
    }

    /**
     * Copy a directory from the running jar file to an external directory
     * within the [outputRootDir]
     */
    fun copyResourceFolderToDirectory(jarDir: String, destDir: String = "") {
        val jar = getCurrentJarFile()
        val entries = jar.entries().iterator()

        entries.forEach { entry ->
            if (entry.name.startsWith("$jarDir/") && !entry.isDirectory) {
                val dest = File("$outputRootDir/$destDir", entry.name.substring(jarDir.length + 1))
                val parent = dest.parentFile
                parent?.mkdirs()

                jar.getInputStream(entry).use { inputStream ->
                    dest.outputStream().use { outputStream ->
                        inputStream.reader().useLines { lines ->
                            outputStream.writer().use { writer ->
                                lines.forEach { line ->
                                    writer.appendln(line)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Return an instance representing the running
     * jar file
     */
    private fun getCurrentJarFile(): JarFile {
        val path = javaClass.protectionDomain.codeSource.location.path
        val decodedPath = URLDecoder.decode(path, "UTF-8")
        return JarFile(decodedPath)
    }

    /**
     * Return the content of the given resource
     * as a String or null if it wasn't be found
     */
    private fun get(resName: String): String? {
        return javaClass.getResourceAsStream("/$resourcesRootDir/$resName").use { inputStream ->
            inputStream?.reader()?.useLines { lines ->
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