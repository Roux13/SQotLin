package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.SqlComparable

infix fun SqlComparable.AND(right: String): String {
    val left = this
    return "$left AND $right"
}

infix fun SqlComparable.OR(right: String): String {
    val left = this
    return "$left OR $right"
}

infix fun SqlComparable.ON(clause: String): String {
    return "$this ON $clause"
}

infix fun SqlComparable.IN(clause: String): String {
    return "$this IN $clause"
}

infix fun SqlComparable.EQ(right: String): String {
    return "$this = $right"
}

infix fun SqlComparable.NEQ(right: String): String {
    return "$this != $right"

}