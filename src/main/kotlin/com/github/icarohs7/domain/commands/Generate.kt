package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.subcommands
import com.github.icarohs7.domain.commands._base.BaseCommand

class Generate private constructor() : BaseCommand("Generate a new component") {
    override fun run(): Unit = Unit

    companion object {
        fun create(): Generate {
            return Generate()
                    .subcommands(
                            AndroidApp(),
                            AndroidLibrary(),
                            JavafxApp(),
                            JvmMppLibrary(),
                            JvmLibrary(),
                            KtorServerApp()
                    )
        }
    }
}