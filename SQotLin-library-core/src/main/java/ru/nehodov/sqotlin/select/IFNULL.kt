package ru.nehodov.sqotlin.select

import ru.nehodov.sqotlin.Aliasable

class IFNULL(
        private val checked: String,
        private val default: String
) {

    constructor(
            groupFun: SUM,
            default: String
    ) : this(groupFun.column, default)

    constructor(
            groupFun: SUM,
            default: Any
    ) : this(groupFun.column, default.toString())

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

infix fun IFNULL.AS(table: Aliasable): String {
    return "${this} ${table.alias}"
}

infix fun IFNULL.AND(right: String): String {
    val left = this
    return "$left AND $right"
}

infix fun IFNULL.OR(right: String): String {
    val left = this
    return "$left OR $right"
}

infix fun IFNULL.ON(clause: String): String {
    return "$this ON $clause"
}

infix fun IFNULL.IN(clause: String): String {
    return "$this IN $clause"
}

infix fun IFNULL.EQ(operand: String): String {
    return "$this = $operand"
}

infix fun IFNULL.NEQ(operand: String): String {
    return "$this != $operand"
}
