package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.subcommands

/**
 * Base command used only to group
 * other subcommands
 */
class Android private constructor() : BaseCommand(name = "") {
    override fun run(): Unit = Unit

    companion object {
        fun launch(args: Array<String>) =
                Android()
                        .subcommands(New.create(), Generate.create())
                        .main(args)
    }
}