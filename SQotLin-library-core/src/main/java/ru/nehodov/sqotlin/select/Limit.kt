package ru.nehodov.sqotlin.select

class Limit(private val query: SelectQuery, limit: Int): ISelect {

    init {
        query.addLimit(limit)
    }

    fun OFFSET(offset: Int): Offset {
        return Offset(query, offset)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}