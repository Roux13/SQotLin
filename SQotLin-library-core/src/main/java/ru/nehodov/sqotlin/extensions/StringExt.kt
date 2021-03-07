package ru.nehodov.sqotlin.extensions

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