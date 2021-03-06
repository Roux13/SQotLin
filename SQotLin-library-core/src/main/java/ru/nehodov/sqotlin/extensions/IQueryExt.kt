package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.ISelect

private const val AS = "AS"

infix fun ISelect.AS(alias: String): String {
    val as_alias = if (alias.isNotEmpty()) " $AS $alias" else ""
    return """
            |${AS_IS()}$as_alias
        """.trimMargin("|")
}

fun ISelect.AS_IS(): String {
    return this.subQuery()
}