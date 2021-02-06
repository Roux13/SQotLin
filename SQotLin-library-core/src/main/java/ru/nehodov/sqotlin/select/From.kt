package ru.nehodov.sqotlin.select

class From(select: ISelect, vararg tableList: String): ISelect {

    private var query: String

    init {
        query = """
            |${select.sql()}
            |$FROM
        """.trimMargin()
        for (i in tableList.indices) {
            query =
                """|$query
                   |   ${tableList[i]},
                   """.trimMargin("|")

        }
        query = query.trimEnd(',')
    }

    fun WHERE(rowFilter: String): Where {
        return Where(this, rowFilter)
    }

    fun INNER_JOIN(table: String): Join {
        return InnerJoin(this, table)
    }

    fun CROSS_JOIN(table: String): Join {
        return CrossJoin(this, table)
    }

    fun LEFT_JOIN(table: String): Join {
        return LeftJoin(this, table)
    }

    override fun sql() = query

    companion object {
        private const val FROM = "FROM"
    }
}