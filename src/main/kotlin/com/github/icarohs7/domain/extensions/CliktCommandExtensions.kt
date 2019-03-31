package com.github.icarohs7.domain.extensions

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.icarohs7.domain.commands.BaseCommand

/**
 * Create the given subcommand and a second command
 * using the given [alias] calling the first when
 * executed
 */
fun CliktCommand.subcommandWithAlias(command: CliktCommand, alias: String): CliktCommand {
    val aliasCommand = object : BaseCommand(name = alias, help = command.commandHelp) {
        override fun run() = command.run()
    }

    return subcommands(command, aliasCommand)
}