package com.github.icarohs7

import com.github.icarohs7.domain.commands.Root
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main(args: Array<String>) {
    setupKoin()
    Root.launch(args)
}

private fun setupKoin() {
    startKoin {
        modules(module {
        })
    }
}