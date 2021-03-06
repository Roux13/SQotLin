package ru.nehodov.sqotlin.select

class GroupBy(private val query: SelectQuery, vararg groupClause: String) : ISelect {

    init {
        query.addGroupBy(*groupClause)
    }

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    fun HAVING(expr: String): Having {
        return Having(query, expr)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}