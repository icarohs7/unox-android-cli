package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.subcommands

/**
 * Base command used only to group
 * other subcommands
 */
class Android : BaseCommand(name = "") {
    override fun run(): Unit = Unit

    companion object {
        fun launch(args: Array<String>) =
                Android()
                        .subcommands(New(), Generate())
                        .main(args)
    }
}