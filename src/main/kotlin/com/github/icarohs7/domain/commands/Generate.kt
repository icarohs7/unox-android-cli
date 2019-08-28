package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.subcommands

class Generate private constructor() : BaseCommand("Generate a new component") {
    override fun run(): Unit = Unit

    companion object {
        fun create(): Generate {
            return Generate()
                    .subcommands(
                            AndroidApp.create(),
                            AndroidLibrary.create(),
                            JavafxApp.create(),
                            JavaMppLibrary.create()
                    )
        }
    }
}