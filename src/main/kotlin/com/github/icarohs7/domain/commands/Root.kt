package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.subcommands
import com.github.icarohs7.domain.commands._base.BaseCommand

class Root private constructor() : BaseCommand(name = "") {
    override fun run(): Unit = Unit

    companion object {
        fun launch(args: Array<String>) =
                Root()
                        .subcommands(New.create(), Generate.create())
                        .main(args)
    }
}