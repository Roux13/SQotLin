package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.ISelect

infix fun ISelect.UNION_ALL(other: ISelect): String {
    return """
        |${this.query()}
        |UNION ALL
        |${other.query()}
    """.trimMargin()
}

infix fun String.UNION_ALL(other: ISelect): String {
    return """
        |${this}
        |UNION ALL
        |${other.query()}
    """.trimMargin()
}

infix fun ISelect.UNION(other: ISelect): String {
    return """
        |${this.query()}
        |UNION
        |${other.query()}
    """.trimMargin()
}

infix fun String.UNION(other: ISelect): String {
    return """
        |${this}
        |UNION
        |${other.query()}
    """.trimMargin()
}

infix fun ISelect.UNION_ALL(other: String): String {
    return """
        |${this.query()}
        |UNION ALL
        |$other
    """.trimMargin()
}

infix fun String.UNION_ALL(other: String): String {
    return """
        |$this
        |UNION ALL
        |$other
    """.trimMargin()
}

infix fun ISelect.UNION(other: String): String {
    return """
        |${this.query()}
        |UNION
        |$other
    """.trimMargin()
}

infix fun String.UNION(other: String): String {
    return """
        |$this
        |UNION
        |$other
    """.trimMargin()
}