package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.icarohs7.data.entities.Project

/**
 * Used to create new projects
 */
class New : BaseCommand("Create a new Android project") {
    private val projectName by argument(help = "Name of the project")

    override fun run() {
        Project(projectName).createOnDisk().unsafeRunSync()
    }
}