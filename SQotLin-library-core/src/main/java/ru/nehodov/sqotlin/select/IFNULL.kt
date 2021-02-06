package ru.nehodov.sqotlin.select

import ru.nehodov.sqotlin.aggregateFunctions.AggregateFunc

class IFNULL(
        private val checked: String,
        private val default: String
) {

    constructor(
            groupFun: AggregateFunc,
            default: String
    ) : this(groupFun.AS_IS(), default)

    constructor(
            groupFun: AggregateFunc,
            default: Any
    ) : this(groupFun.AS_IS(), default.toString())

    constructor(
            checked: String,
            default: Any
    ) : this(checked, default.toString())

    infix fun AS(alias: String): String {
        val as_alias = if (alias.isNotEmpty()) " $AS $alias" else ""
        return """
            |$IFNULL($checked, $default)$as_alias
        """.trimMargin("|")
    }

    fun AS_IS(): String {
        return AS("")
    }

    companion object {
        private const val IFNULL = "IFNULL"
        private const val AS = "AS"
    }
}


