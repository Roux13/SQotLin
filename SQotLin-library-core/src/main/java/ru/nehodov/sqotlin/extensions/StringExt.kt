package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.ISelect

infix fun String.AS(alias: String): String {
    val as_alias = if (alias.isNotEmpty()) " AS $alias" else ""
    return """
        |$this$as_alias
        """.trimMargin("|")
}

infix fun String.AND(right: String): String {
    val left = this
    return if (right.isEmpty()) {
        this
    } else
        """
       |$left
       |   AND $right
    """.trimMargin()
}

infix fun String.OR(right: String): String {
    val left = this
    return "($left OR $right)"
}

fun String.IN(vararg valueArgs: String, negation: Boolean = false): String {
    val NOT = if (negation) "NOT " else ""
    var values = valueArgs[0]
    for (i in 1..valueArgs.lastIndex) {
        values =
            """
            |$values, ${valueArgs[i]}
            """.trimMargin("|")
    }
    return """$this ${NOT}IN ($values)"""
}

fun String.IN(subQuery: ISelect, negation: Boolean = false): String {
    val NOT = if (negation) "NOT " else ""
    val _in = StringBuilder("|$this ${NOT}IN ")
    val inWithSpaceLength = " ${NOT}IN ".length
    val indent = " ".repeat(this.length + inWithSpaceLength)
    val subQueryLines = subQuery.subQuery().trimMargin().split("\n")
    for (i in subQueryLines.indices) {
        if (i == 0) {
            _in.append(subQueryLines[i])
        } else {
            _in.appendLine().append("|$indent${subQueryLines[i]}")
        }
    }

    return _in.toString().trimMargin().trimEnd()
}

fun String.IN(vararg valueArgs: Any): String {
    val strings = valueArgs.map { it.toString() }.toTypedArray()
    return IN(*strings, negation = false)
}

fun String.NOT_IN(subQuery: ISelect): String {
    return IN(subQuery, negation = true)
}

fun String.NOT_IN(vararg valueArgs: Any): String {
    val strings = valueArgs.map { it.toString() }.toTypedArray()
    return IN(*strings, negation = true)
}

infix fun String.LIKE(pattern: String): String {
    return """
        |LIKE '$pattern'
    """.trimMargin()
}

fun String.IS_NULL(): String {
    return """
        |$this IS NULL
    """.trimMargin()
}

fun String.IS_NOT_NULL(): String {
    return """
        |$this IS NOT NULL
    """.trimMargin()
}

fun String.ASC(): String {
    return """
        |$this ASC
    """.trimMargin()
}

fun String.DESC(): String {
    return """
        |$this DESC
    """.trimMargin()
}

infix fun String.BETWEEN(low: Any): Between {
    return Between(value = this, low.toString())
}

infix fun String.NOT_BETWEEN(low: Any): Between {
    return Between(value = this, low.toString(), negation = true)
}

infix fun String.EQ(right: String): String {
    val operator = "="
    return comparisonOperation(this, operator, right)
}

infix fun String.EQ(right: Any): String {
    return this.EQ(right.toString())
}

infix fun String.NEQ(right: String): String {
    val operator = "!="
    return comparisonOperation(this, operator, right)
}

infix fun String.NEQ(right: Any): String {
    return this.NEQ(right.toString())
}

infix fun String.LESS(right: String): String {
    val operator = "<"
    return comparisonOperation(this, operator, right)
}

infix fun String.LESS(right: Any): String {
    return this.LESS(right.toString())
}

infix fun String.GREATER(right: String): String {
    val operator = ">"
    return comparisonOperation(this, operator, right)
}

infix fun String.GREATER(right: Any): String {
    return this.GREATER(right.toString())
}

infix fun String.LESSorEQ(right: String): String {
    val operator = "<="
    return comparisonOperation(this, operator, right)
}

infix fun String.LESSorEQ(right: Any): String {
    return this.LESSorEQ(right.toString())
}

infix fun String.GREATorEQ(right: String): String {
    val operator = ">="
    return comparisonOperation(this, operator, right)
}

infix fun String.GREATorEQ(right: Any): String {
    return this.GREATorEQ(right.toString())
}

private fun comparisonOperation(left: String, operator: String, right: String): String {
    return """
       |$left $operator $right
    """.trimMargin("|")
}