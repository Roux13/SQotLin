package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.aggregateFunctions.AggregateFunc

private val AS = "AS"

infix fun AggregateFunc.AS(alias: String): String {
    return "${this.funcName}(${this.arg})${takeAliasString(alias)}"
}

fun AggregateFunc.AS_IS(): String {
    return this.AS("")
}

private fun takeAliasString(alias: String): String {
    return if (alias.isNotEmpty()) {
        " $AS $alias"
    } else {
        ""
    }
}