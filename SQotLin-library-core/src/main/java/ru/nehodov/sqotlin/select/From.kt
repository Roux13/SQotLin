package ru.nehodov.sqotlin.select

class From(private val query: SelectQuery, vararg tableList: String) : ISelect {

    constructor(query: SelectQuery, subQuery: ISelect) : this(query, subQuery.subQuery())

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

    fun INNER_JOIN(subQuery: ISelect): Join {
        return InnerJoin(query, subQuery.subQuery())
    }

    fun CROSS_JOIN(subQuery: ISelect): Join {
        return CrossJoin(query, subQuery.subQuery())
    }

    fun LEFT_JOIN(subQuery: ISelect): Join {
        return LeftJoin(query, subQuery.subQuery())
    }

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    fun LIMIT(limit: Int): Limit {
        return Limit(query, limit)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}