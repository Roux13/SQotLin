package ru.nehodov.sqotlin

import ru.nehodov.sqotlin.select.IFNULL

//infix fun IFNULL.AS(table: Aliasable): String {
//    return "${this} ${table.alias}"
//}

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