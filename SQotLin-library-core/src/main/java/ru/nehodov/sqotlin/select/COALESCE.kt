package ru.nehodov.sqotlin.select

class COALESCE(
        vararg checked: String,
        val default: String = ""
) {

    constructor(vararg checked: String, default: Int) : this(*checked, default.toString())
    constructor(vararg checked: Any, default: Any) : this(*checked.map { it.toString() }.toTypedArray(), default.toString())

    val COALESCE = "COALESCE"

    var allChecked = ""

    init {
        checked.forEach {
            allChecked.plus("$it, ")
        }
        allChecked.trimEnd(' ').trimEnd(',')
    }
}