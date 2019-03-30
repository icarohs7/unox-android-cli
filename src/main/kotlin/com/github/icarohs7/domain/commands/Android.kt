package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class Android : CliktCommand() {
    override fun run(): Unit = Unit

    companion object {
        fun launch(args: Array<String>) {
            println(args.toList())
            Android()
                    .subcommands(New())
                    .main(args)
        }
    }
}