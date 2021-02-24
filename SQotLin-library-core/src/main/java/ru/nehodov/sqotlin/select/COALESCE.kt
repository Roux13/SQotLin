package ru.nehodov.sqotlin.select

import ru.nehodov.sqotlin.Aliasable
import ru.nehodov.sqotlin.SQLiteConst

class COALESCE(
    vararg checked: String,
    val default: String = "''",
) : Aliasable {

    constructor(vararg checked: String, default: Int) : this(*checked, default = default.toString())
    constructor(vararg checked: Any, default: Any) : this(
        *checked.map { it.toString() }.toTypedArray(),
        default = default.toString()
    )

    val COALESCE = "COALESCE"

    var clause = ""

    init {
        clause = checked.joinToString(", ")
    }


    override fun toString(): String {
        return """
            |$COALESCE($clause, ${if (default.isEmpty()) SQLiteConst.EMPTY else default})
            """.trimMargin()
    }


}