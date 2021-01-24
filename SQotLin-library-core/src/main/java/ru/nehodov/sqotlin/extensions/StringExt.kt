package ru.nehodov.sqotlin

infix fun String.AS(alias: String): String {
    val as_alias = if (alias.isNotEmpty()) " AS $alias" else ""
    return """
        |$this$as_alias
        """.trimMargin("|")
}

infix fun String.AS(table: Aliasable): String {
    return """
        |$this ${table.alias}
        """.trimMargin("|")
}

infix fun String.AND(right: String): String {
    val left = this
    return "$left AND $right"
}

infix fun String.OR(right: String): String {
    val left = this
    return "$left OR $right"
}

infix fun String.ON(clause: String): String {
    return "$this ON $clause"
}

fun String.IN(vararg valueArgs: String): String {
    var values = valueArgs[0]
    for (i in 1..valueArgs.lastIndex) {
        values =
            """
            |$values, ${valueArgs[i]}
            """.trimMargin("|")
    }
    return """$this IN ($values)"""
}

fun String.IN(vararg valueArgs: Any): String {
    val strings = valueArgs.map { it.toString() }.toTypedArray()
    return this.IN(*strings)
}

infix fun String.LIKE(pattern: String): String {
    return """
        |LIKE '$pattern'
    """.trimMargin()
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

infix fun String.GREAT(right: String): String {
    val operator = ">"
    return comparisonOperation(this, operator, right)
}

infix fun String.GREAT(right: Any): String {
    return this.GREAT(right.toString())
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