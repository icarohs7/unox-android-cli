package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.icarohs7.data.entities.Project
import com.github.icarohs7.domain.extensions.not

class New private constructor() : BaseCommand("Create a new project") {
    private val projectName by argument(help = "Name of the project")

    override fun run() {
        !!Project(projectName).createOnDisk()
    }

    companion object {
        fun create(): New {
            return New()
        }
    }
}