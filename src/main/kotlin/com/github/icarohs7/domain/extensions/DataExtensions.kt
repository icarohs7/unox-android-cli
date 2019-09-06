package com.github.icarohs7.domain.extensions

import com.github.icarohs7.data.entities.Module
import com.github.icarohs7.data.local.ResourceDao
import java.io.File

fun Module.createAndroidAppOnDisk() {
    val rootDir = "androidapp"
    copyContentsOfResDir(packageDestination, rootDir) {
        fixContents(copy("AndroidManifest.xml"))
        fixContents(copyResourceFolderToDirectory("$rootDir/content/res", "/res"))
    }
}

fun Module.createAndroidLibraryOnDisk() {
    val rootDir = "androidlibrary"
    copyContentsOfResDir(packageDestination, rootDir) {
        fixContents(copy("AndroidManifest.xml"))
        File("$name/src/main/res").mkdirs()
    }
}

fun Module.createJvmJavaFxAppOnDisk() {
    val rootDir = "javafxapp"
    copyContentsOfResDir(packageDestination, rootDir) {
        File("$name/src/main/resources").mkdirs()
        fixContents(copyResourceFolderToDirectory("$rootDir/content/resources", "/resources"))
    }
}

fun Module.createJvmMppLibraryOnDisk() {
    val rootDir = "jvmmpplibrary"
    copyContentsOfResDir(packageDestination, rootDir) {
        File("$name/src/main/resources").mkdirs()
        fixContents(copyResourceFolderToDirectory("$rootDir/content/resources", "/resources"))
    }
}

fun Module.createJvmLibraryOnDisk() {
    val rootDir = "jvmlibrary"
    copyContentsOfResDir(packageDestination, rootDir) {
        File("$name/src/main/resources").mkdirs()
        fixContents(copyResourceFolderToDirectory("$rootDir/content/resources", "/resources"))
    }
}

fun Module.createKtorServerAppOnDisk() {
    val rootDir = "ktorserverapp"
    copyContentsOfResDir(packageDestination, rootDir) {
        File("$name/src/main/resources").mkdirs()
        fixContents(copyResourceFolderToDirectory("$rootDir/content/resources", "/resources"))
    }
}

private fun Module.copyContentsOfResDir(
        packageDestination: String,
        rootDir: String,
        extraBlock: ResourceDao.() -> Unit = {}
) {
    copyBuildscript(rootDir)

    ResourceDao.use("$rootDir/content", "$name/src/main") {
        fixContents(copyResourceFolderToDirectory("$rootDir/content/code", "kotlin/$packageDestination"))
        File("$name/src/main/kotlin/$packageDestination/data").mkdirs()
        File("$name/src/main/kotlin/$packageDestination/domain").mkdirs()
        File("$name/src/main/kotlin/$packageDestination/presentation").mkdirs()
        File("$name/src/test/kotlin/$packageDestination").mkdirs()
        extraBlock()
    }
}

private fun Module.copyBuildscript(rootDir: String) {
    ResourceDao.use(rootDir, name) {
        fixContents(copy("build.gradle.kts"))
    }
}

private fun Module.fixContents(file: File) {
    if (file.isFile)
        file.replaceCurlyVariables(replaces)
    else {
        file.recursiveChildrenSequence().forEach {
            if (it.isFile)
                it.replaceCurlyVariables(replaces)
        }
    }
}