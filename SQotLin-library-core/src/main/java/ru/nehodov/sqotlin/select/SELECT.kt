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

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    override fun sql(): String = query.sql()
    override fun toString() = sql()

}






