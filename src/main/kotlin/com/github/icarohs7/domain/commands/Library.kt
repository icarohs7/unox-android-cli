package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.icarohs7.data.entities.Module

/**
 * Used to generate new library modules
 */
class Library private constructor() : BaseCommand("Generate a new library module") {
    private val moduleName by argument(
            help = "Complete name of the module, including its group. e.g: com.github.user.module"
    )

    override fun run() {
        val parts = moduleName.split(".")
        val group = parts.dropLast(1).joinToString(separator = ".")
        val module = parts.last()
        Module(group, module).createLibraryOnDisk().unsafeRunSync()
    }

    companion object {
        fun create(): Library {
            return Library()
        }
    }
}