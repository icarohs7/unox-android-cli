package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.parameters.arguments.argument

/**
 * Used to generate new library modules
 */
class Library private constructor() : BaseCommand("Generate a new library module") {
    private val moduleName by argument(
            help = "Complete name of the module, including its group. e.g: com.github.user.module"
    )

    override fun run() {
    }

    companion object {
        fun create(): Library {
            return Library()
        }
    }
}