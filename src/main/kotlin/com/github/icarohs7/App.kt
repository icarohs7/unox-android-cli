package com.github.icarohs7

import com.github.icarohs7.domain.commands.Android
import org.koin.dsl.module.module
import org.koin.log.EmptyLogger
import org.koin.standalone.StandAloneContext.startKoin


fun main(args: Array<String>) {
    setupKoin()
    Android.launch(args)
}

private fun setupKoin() {
    startKoin(listOf(module {
    }), logger = EmptyLogger())
}
