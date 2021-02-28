package ru.nehodov.sqotlin.select

open class SELECT(
    vararg columns: String,
    isDistinct: Boolean = false,
) : ISelect {
    private val query: SelectQuery = SelectQuery(isDistinct)

    init {
        query.setColumns(*columns)
    }

    fun FROM(vararg tableList: String): From {
        return From(query, *tableList)
    }

    fun FROM(subQuery: ISelect): From {
        return From(query, subQuery)
    }

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}






