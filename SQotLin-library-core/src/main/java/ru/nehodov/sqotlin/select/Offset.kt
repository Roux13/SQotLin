package ru.nehodov.sqotlin.select

class Offset(private val query: SelectQuery, offset: Int) : ISelect {

    init {
        query.addOffset(offset)
    }

    override fun sql(): String = query.sql()
}