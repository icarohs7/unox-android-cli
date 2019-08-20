package com.github.icarohs7.data.repository

import org.eclipse.jgit.api.Git
import java.io.File

class GitRepository {
    fun cloneRepository(url: String, destinationFolder: File) {
        destinationFolder.mkdirs()
        Git.cloneRepository()
                .setURI(url)
                .setDirectory(destinationFolder)
                .call()
                .close()
    }
}