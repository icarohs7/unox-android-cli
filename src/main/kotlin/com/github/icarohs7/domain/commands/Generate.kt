package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.icarohs7.data.entities.Module

/**
 * Used to generate new components, like
 * application and library modules
 */
class Generate : BaseCommand("Generate a new component") {
    private val moduleName by argument(
            help = "Complete name of the module, including its group. e.g: com.github.user.module"
    )

    override fun run() {
        val parts = moduleName.split(".")
        val group = parts.dropLast(1).joinToString(separator = ".")
        val module = parts.last()
        Module(group, module).createStandaloneOnDisk().unsafeRunSync()
    }

    companion object {
        fun create(): Generate {
            return Generate()
        }
    }
}