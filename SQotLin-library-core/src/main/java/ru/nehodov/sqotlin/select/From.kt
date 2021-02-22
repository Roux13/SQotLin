package ru.nehodov.sqotlin.select

class From(private val query: SelectQuery, vararg tableList: String) : ISelect {

    init {
        query.setTables(*tableList)
    }

    fun WHERE(rowFilter: String): Where {
        return Where(query, rowFilter)
    }

    fun GROUP_BY(vararg groupClause: String): GroupBy {
        return GroupBy(query, *groupClause)
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

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    fun LIMIT(limit: Int): Limit {
        return Limit(query, limit)
    }

    override fun sql(): String = query.sql()
}