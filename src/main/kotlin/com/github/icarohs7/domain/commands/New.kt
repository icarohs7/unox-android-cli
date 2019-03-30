package com.github.icarohs7.domain.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.icarohs7.data.templates.gradle.RootBuild
import com.github.icarohs7.data.templates.gradle.Settings
import com.github.icarohs7.domain.Injector
import org.koin.standalone.get

class New : CliktCommand(help = "Create a new Android project") {
    val projectName by argument(help = "Name of the project")

    override fun run() {
        val result1 = Injector.get<Settings>().write(projectName)
        val result2 = Injector.get<RootBuild>().write(projectName)
        echo(result1.unsafeRunSync())
        echo(result2.unsafeRunSync())
    }
}