package com.github.icarohs7.domain.commands._base

import com.github.ajalt.clikt.core.CliktCommand

abstract class BaseCommand(
        help: String = "",
        name: String? = null
) : CliktCommand(help = help, printHelpOnEmptyArgs = true, name = name)