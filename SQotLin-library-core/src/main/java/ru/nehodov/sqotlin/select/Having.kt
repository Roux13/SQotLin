package ru.nehodov.sqotlin.select

class Having(private val query: SelectQuery, expr: String): ISelect {

    init {
        query.addHaving(expr)
    }

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}