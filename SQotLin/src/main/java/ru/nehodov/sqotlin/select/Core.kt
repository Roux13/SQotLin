package ru.nehodov.sqotlin.core.select

import ru.nehodov.sqotlin.DbConst


open class SELECT(
    vararg columns: String
) {
    var query: String = SELECT

    init {
        for (i in columns.indices) {
            query = if (i == columns.lastIndex) {
                """|$query
                   |   ${columns[i]}
                   """.trimMargin("|")
            } else {
                """|$query
                   |   ${columns[i]},
                   """.trimMargin("|")
            }
        }
    }

    fun FROM(where: String, AS: String = ""): SELECT {
        val alias = if (AS.isNotEmpty()) " $AS" else ""
        return this.apply {
            query = """
            |$query
            |$FROM
            |   $where$alias
            """.trimMargin()
        }
    }

    fun INNER_JOIN(select: SELECT, alias: String = ""): JOIN {
        val as_alias = if (alias.isNotEmpty()) "AS $alias" else ""
        return JOIN("$query\n  JOIN ${select.query + as_alias}")
    }

    fun INNER_JOIN(table: String, alias: String = ""): JOIN {
        val as_alias = if (alias.isNotEmpty()) "AS $alias" else ""
        return JOIN("$query\n   JOIN $table + $as_alias}")
    }

    fun WHERE(clause: String): SELECT {
        return this.apply {
            query = """
                |${query}
                |$WHERE
                |   $clause
                """.trimMargin()
        }
    }

    fun GROUP_BY(clause: String): SELECT {
        return SELECT(
            """|$query
                         |GROUP BY $clause
                         """.trimMargin("|")
        )
    }

    fun get(): String = query

    companion object {
        const val SELECT = "SELECT"
        const val FROM = "FROM"
        const val WHERE = "WHERE"
    }
}

const val EMPTY = "''"
const val O = "0"
const val ALL = " * "

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
        default: Int
    ) : this(groupFun.column, default.toString())

    constructor(
        checked: String,
        default: Int
    ) : this(checked, default.toString())

    infix fun AS(alias: String): String {
        val as_alias = if (alias.isNotEmpty()) "AS $alias" else ""
        return "IFNULL($checked, $default) $as_alias"
    }

    fun AS_IS(): String {
        return AS("")
    }
}

class COALESCE(
    vararg checked: String,
    private val default: String = ""
) {

    constructor(vararg checked: String, default: Int) : this(*checked, default.toString())

    private val COALESCE = "COALESCE"

    var allCheked = ""

    init {
        checked.forEach {
            allCheked.plus("$it, ")
        }
        allCheked.trimEnd(' ').trimEnd(',')
    }

    infix fun AS(alias: String): String {
        val as_alias = if (alias.isNotEmpty()) "AS $alias" else ""
        return "$COALESCE($allCheked, $default) $as_alias"
    }

    fun AS_IS(): String {
        return AS("")
    }
}

infix fun IFNULL.AS(table: DbConst): String {
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

class SUM(
    val column: String
)

class JOIN(query: String) : SELECT(query)

