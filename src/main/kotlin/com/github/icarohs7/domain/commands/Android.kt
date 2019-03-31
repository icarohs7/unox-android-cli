package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.subcommands
import com.github.icarohs7.domain.extensions.subcommandWithAlias

class Android : BaseCommand(name = "") {
    override fun run(): Unit = Unit

    companion object {
        fun launch(args: Array<String>) =
                Android()
                        .subcommands(New())
                        .subcommandWithAlias(Generate.create(), "g")
                        .main(args)
    }
}