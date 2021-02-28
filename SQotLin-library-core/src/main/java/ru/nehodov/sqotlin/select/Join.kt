package ru.nehodov.sqotlin.select

open class Join(private val query: SelectQuery, joinType: String, table: String): ISelect {

    init {
        query.addJoin("$joinType $table")
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

    fun WHERE(rowFilter: String): Where {
        return Where(query, rowFilter)
    }

    fun GROUP_BY(vararg groupClause: String): GroupBy {
        return GroupBy(query, *groupClause)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}