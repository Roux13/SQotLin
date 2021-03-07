package ru.nehodov.sqotlin.extensions

data class Between(val value: String, val low: String, val negation: Boolean = false)

infix fun Between.AND(high: Any): String {
    val negation = if (negation) "NOT " else ""
    return """
        |$value ${negation}BETWEEN $low AND $high
    """.trimMargin()
}