package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.COALESCE

infix fun COALESCE.AS(alias: String): String {
    val asAlias = if (alias.isNotEmpty()) "AS $alias" else ""
    return "$COALESCE($allChecked, $default) $asAlias"
}

fun COALESCE.AS_IS(): String {
    return AS("")
}