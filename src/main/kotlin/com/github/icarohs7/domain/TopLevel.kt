package com.github.icarohs7.domain

fun noFalse(msg: String = "", block: () -> Boolean) {
    if (!block())
        throw IllegalStateException(msg)
}