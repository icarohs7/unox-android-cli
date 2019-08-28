package com.github.icarohs7.data.entities

import arrow.core.Tuple2
import arrow.core.toTuple2

/**
 * Represent a child module of a project
 */
class Module(group: String, val name: String) {
    val completeName = "$group.$name".replace("-", "")
    val packageDestination = completeName.replace(".", "/")
    val replaces: List<Tuple2<String, String>> = listOf(
            "module.group" to group,
            "module.name" to name,
            "module.flatName" to name.replace("-", ""),
            "module.completeName" to completeName
    ).map { it.toTuple2() }
}