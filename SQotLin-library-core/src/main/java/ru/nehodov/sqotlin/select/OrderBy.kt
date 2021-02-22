package ru.nehodov.sqotlin.select

class OrderBy(private val query: SelectQuery, vararg orderingTerms: String): ISelect {

    init {
        query.addOrderBy(*orderingTerms)
    }

    fun LIMIT(limit: Int): Limit {
        return Limit(query, limit)
    }

    override fun sql(): String = query.sql()
}