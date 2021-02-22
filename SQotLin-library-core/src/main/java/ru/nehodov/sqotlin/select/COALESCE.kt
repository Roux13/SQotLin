package ru.nehodov.sqotlin.select

import ru.nehodov.sqotlin.Aliasable

class COALESCE(
    vararg checked: String,
    val default: String = "",
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

//    infix fun AS(alias: String): String {
//        val asAlias = if (alias.isNotEmpty()) " AS $alias" else ""
//        return "$COALESCE($clause, $default)$asAlias"
//    }
//
//    fun AS_IS(): String {
//        return AS("")
//    }

    override fun toString(): String {
        return """
            |$COALESCE($clause, $default)
            """.trimMargin()
    }


}