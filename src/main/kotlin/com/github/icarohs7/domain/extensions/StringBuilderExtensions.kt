package com.github.icarohs7.domain.extensions

fun StringBuilder.include(modules: List<String>): Unit = modules.forEach(::include)
fun StringBuilder.include(module: String) {
    appendln("include($module)")
}