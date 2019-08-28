package com.github.icarohs7.domain.commands._base

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.icarohs7.data.entities.Module

@Suppress("LeakingThis")
abstract class BaseModuleCreationCommand(help: String = "", name: String? = null) : BaseCommand(help, name) {
    private val moduleName by argument(
            help = "Complete name of the module, including its group. e.g: com.github.user.module")

    abstract fun onCreateModule(module: Module)
    override fun run() {
        val parts = moduleName.split(".")
        val group = parts.dropLast(1).joinToString(separator = ".")
        val module = parts.last()
        onCreateModule(Module(group, module))
    }
}