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

infix fun SqlComparable.LESS(right: String): String {
    val operator = "<"
    return comparableComparisonOperation(this.toString(), operator, right)
}

infix fun SqlComparable.LESS(right: Any): String {
    return this.LESS(right.toString())
}

infix fun SqlComparable.GREATER(right: String): String {
    val operator = ">"
    return comparableComparisonOperation(this.toString(), operator, right)
}

infix fun SqlComparable.GREATER(right: Any): String {
    return this.GREATER(right.toString())
}

infix fun SqlComparable.LESSorEQ(right: String): String {
    val operator = "<="
    return comparableComparisonOperation(this.toString(), operator, right)
}

infix fun SqlComparable.LESSorEQ(right: Any): String {
    return this.LESSorEQ(right.toString())
}

infix fun SqlComparable.GREATorEQ(right: String): String {
    val operator = ">="
    return comparableComparisonOperation(this.toString(), operator, right)
}

infix fun SqlComparable.GREATorEQ(right: Any): String {
    return this.GREATorEQ(right.toString())
}

private fun comparableComparisonOperation(left: String, operator: String, right: String): String {
    return """
       |$left $operator $right
    """.trimMargin("|")
}