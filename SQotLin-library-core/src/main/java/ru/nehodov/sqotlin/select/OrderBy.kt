package ru.nehodov.sqotlin.select

class OrderBy(private val query: SelectQuery, vararg orderingTerms: String): ISelect {

    init {
        query.addOrderBy(*orderingTerms)
    }

    fun LIMIT(limit: Int): Limit {
        return Limit(query, limit)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}