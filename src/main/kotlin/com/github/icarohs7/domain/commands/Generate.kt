package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.subcommands

/**
 * Used to generate new components, like
 * application and library modules
 */
class Generate private constructor() : BaseCommand("Generate a new component") {
    override fun run(): Unit = Unit

    companion object {
        fun create(): Generate {
            return Generate()
                    .subcommands(App.create(), Library.create())
        }
    }
}