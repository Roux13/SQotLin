package ru.nehodov.sqotlin.select

class Having(private val query: SelectQuery, expr: String): ISelect {

    init {
        query.addHaving(expr)
    }

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    override fun sql(): String = query.sql()
}