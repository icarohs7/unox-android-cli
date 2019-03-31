package com.github.icarohs7.domain.commands

class Generate : BaseCommand("Generate a new component") {
    override fun run(): Unit = Unit

    companion object {
        fun create(): Generate {
            return Generate()
        }
    }
}