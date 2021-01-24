package ru.nehodov.sqotlin.select

open class SELECT(
        vararg columns: String
) {
    protected open var query: String = SELECT

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

    protected constructor(_query: String, vararg columns: String) : this(*columns) {
        query = _query
    }

    fun FROM(table: String, AS: String = ""): FROM {
        val alias = if (AS.isNotEmpty()) " $AS" else ""
        return FROM(
                query = """
            |$query
            |$FROM
            |   $table$alias
            """.trimMargin()
        )
    }

    fun FROM(subQuery: SELECT, AS: String = ""): FROM {
        val alias = if (AS.isNotEmpty()) " $AS" else ""
        return FROM(
                query = """
            |$query
            |$FROM
            |   $subQuery $alias
            """.trimMargin()
        )
    }

    fun INNER_JOIN(select: SELECT, alias: String = ""): JOIN {
        val as_alias = if (alias.isNotEmpty()) "AS $alias" else ""
        return JOIN("$query\n  JOIN ${select.query + as_alias}")
    }

    fun INNER_JOIN(table: String, alias: String = ""): JOIN {
        val as_alias = if (alias.isNotEmpty()) "AS $alias" else ""
        return JOIN("$query\n   JOIN $table + $as_alias}")
    }


//    fun GROUP_BY(clause: String): SELECT {
//        return SELECT(
//                """|$query
//                         |GROUP BY $clause
//                         """.trimMargin("|")
//        )
//    }

    open fun build(): String = query
    override fun toString(): String {
        return query
    }


    companion object {
        private const val SELECT = "SELECT"
        private const val SELECT_DISTINCT = "SELECT DISTINCT"
        private const val FROM = "FROM"

    }

    class DISTINCT(
            vararg columns: String
    ) : SELECT() {
        override var query: String = SELECT_DISTINCT

        init {
            for (i in columns.indices) {
                query = """
                       |$query
                       |   ${columns[i]},
                   """.trimMargin("|")
            }
            query = query.trimEnd(',')

        }

        override fun build() = query

    }
}






