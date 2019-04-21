package com.github.icarohs7.domain.extensions

import arrow.core.Tuple2
import java.io.File

fun File.replace(old: String, new: String) {
    val text = readText()
    writeText(text.replace(old, new))
}

fun File.replaceCurlyVariables(replacers: List<Tuple2<String, String>>) {
    replacers.forEach { (old, new) ->
        replace("{{$old}}", new)
    }
}

fun File.recursiveChildrenSequence(): Sequence<File> {
    return sequence {
        val children = listFiles()?.toMutableList() ?: mutableListOf()
        while (children.isNotEmpty()) {
            val current = children.removeAt(0)
            yield(current)
            children += current.listFiles()?.toList().orEmpty()
        }
    }
}