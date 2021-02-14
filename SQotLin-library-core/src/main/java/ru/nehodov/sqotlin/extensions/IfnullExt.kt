package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.IFNULL

private const val IFNULL = "IFNULL"
private const val AS = "AS"

infix fun IFNULL.AS(alias: String): String {
    val as_alias = if (alias.isNotEmpty()) " $AS $alias" else ""
    return """
            |$IFNULL($checked, $default)$as_alias
        """.trimMargin("|")
}

fun IFNULL.AS_IS(): String {
    return AS("")
}

