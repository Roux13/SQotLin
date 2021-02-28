package ru.nehodov.sqotlin.select

class Where(private val query: SelectQuery, rowFilter: String) : ISelect {

    init {
        query.addWhere(rowFilter)
    }

    fun GROUP_BY(vararg groupClause: String): GroupBy {
        return GroupBy(query, *groupClause)
    }

    fun ORDER_BY(vararg orderingTerms: String): OrderBy {
        return OrderBy(query, *orderingTerms)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()

}