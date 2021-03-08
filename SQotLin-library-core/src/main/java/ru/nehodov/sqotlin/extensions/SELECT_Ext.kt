package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.ISelect

infix fun ISelect.UNION_ALL(other: ISelect): String {
    return """
        |${this.subQuery()}
        |UNION ALL
        |${other.subQuery()}
    """.trimMargin()
}

infix fun String.UNION_ALL(other: ISelect): String {
    return """
        |${this}
        |UNION ALL
        |${other.subQuery()}
    """.trimMargin()
}

infix fun ISelect.UNION(other: ISelect): String {
    return """
        |${this.subQuery()}
        |UNION
        |${other.subQuery()}
    """.trimMargin()
}

infix fun String.UNION(other: ISelect): String {
    return """
        |${this}
        |UNION
        |${other.subQuery()}
    """.trimMargin()
}