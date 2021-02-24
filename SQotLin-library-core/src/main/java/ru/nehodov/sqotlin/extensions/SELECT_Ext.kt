package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.ISelect

infix fun ISelect.UNION_ALL(other: ISelect): String {
    return """
        |${this.subQuery()}
        |UNION ALL
        |${other.subQuery()}
    """.trimMargin()
}