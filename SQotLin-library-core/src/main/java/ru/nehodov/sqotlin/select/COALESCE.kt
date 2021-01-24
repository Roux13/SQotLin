package ru.nehodov.sqotlin.select

class COALESCE(
        vararg checked: String,
        private val default: String = ""
) {

    constructor(vararg checked: String, default: Int) : this(*checked, default.toString())

    private val COALESCE = "COALESCE"

    var allChecked = ""

    init {
        checked.forEach {
            allChecked.plus("$it, ")
        }
        allChecked.trimEnd(' ').trimEnd(',')
    }

    infix fun AS(alias: String): String {
        val as_alias = if (alias.isNotEmpty()) "AS $alias" else ""
        return "$COALESCE($allChecked, $default) $as_alias"
    }

    fun AS_IS(): String {
        return AS("")
    }
}