package com.github.icarohs7.domain.commands

/**
 * Used to generate new components, like
 * application and library modules
 */
class Generate : BaseCommand("Generate a new component") {
    override fun run(): Unit = Unit

    companion object {
        fun create(): Generate {
            return Generate()
        }
    }
}