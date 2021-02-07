package ru.nehodov.sqotlin.select

class From(private val query: SelectQuery, vararg tableList: String): ISelect {

    init {
        query.setTables(*tableList)
    }

    fun WHERE(rowFilter: String): Where {
        return Where(query, rowFilter)
    }

    fun INNER_JOIN(table: String): Join {
        return InnerJoin(query, table)
    }

    fun CROSS_JOIN(table: String): Join {
        return CrossJoin(query, table)
    }

    fun LEFT_JOIN(table: String): Join {
        return LeftJoin(query, table)
    }

    override fun sql(): String = query.sql()
}