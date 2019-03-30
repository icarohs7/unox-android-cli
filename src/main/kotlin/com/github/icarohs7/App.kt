package com.github.icarohs7

import com.github.icarohs7.data.templates.gradle.RootBuild
import com.github.icarohs7.data.templates.gradle.Settings
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
        single { Settings() }
        single { RootBuild() }
    }), logger = EmptyLogger())
}
