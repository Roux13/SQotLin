package ru.nehodov.sqotlin.select

class Offset(private val query: SelectQuery, offset: Int) : ISelect {

    init {
        query.addOffset(offset)
    }

    override fun query(): String = query.sql()
    override fun subQuery(): String = query.subQuery()
    override fun toString() = query()
}