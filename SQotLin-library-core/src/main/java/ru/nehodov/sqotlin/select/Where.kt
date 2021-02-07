package ru.nehodov.sqotlin.select

class Where(private val query: SelectQuery, rowFilter: String) : ISelect {

    init {
        query.addWhere(rowFilter)
    }

    override fun sql(): String = query.sql()

}