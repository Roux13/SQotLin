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

infix fun IFNULL.AND(right: String): String {
    val left = this
    return "$left AND $right"
}

infix fun IFNULL.OR(right: String): String {
    val left = this
    return "$left OR $right"
}

infix fun IFNULL.ON(clause: String): String {
    return "$this ON $clause"
}

infix fun IFNULL.IN(clause: String): String {
    return "$this IN $clause"
}

infix fun IFNULL.EQ(operand: String): String {
    return "$this = $operand"
}

infix fun IFNULL.NEQ(operand: String): String {
    return "$this != $operand"
}

