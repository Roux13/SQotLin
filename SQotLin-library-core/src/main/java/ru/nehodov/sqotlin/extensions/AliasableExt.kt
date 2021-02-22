package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.Aliasable
private const val AS = "AS"

infix fun Aliasable.AS(alias: String): String {
    val as_alias = if (alias.isNotEmpty()) " $AS $alias" else ""
    return """
            |${AS_IS()}$as_alias
        """.trimMargin("|")
}

fun Aliasable.AS_IS(): String {
    return this.toString()
}