package com.github.icarohs7

import com.github.icarohs7.data.repository.GitRepository
import com.github.icarohs7.domain.commands.Android
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main(args: Array<String>) {
    setupKoin()
    Android.launch(args)
}

private fun setupKoin() {
    startKoin {
        modules(module {
            single { GitRepository() }
        })
    }
}