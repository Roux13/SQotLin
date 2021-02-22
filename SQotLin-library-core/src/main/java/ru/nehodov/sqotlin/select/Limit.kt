package ru.nehodov.sqotlin.select

class Limit(private val query: SelectQuery, limit: Int): ISelect {

    init {
        query.addLimit(limit)
    }

    fun OFFSET(offset: Int): Offset {
        return Offset(query, offset)
    }

    override fun sql(): String = query.sql()
}