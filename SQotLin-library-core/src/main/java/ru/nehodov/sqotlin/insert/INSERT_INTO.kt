package ru.nehodov.sqotlin.insert

class INSERT_INTO(private val table: String) {

    fun COLUMNS(vararg columnNames: String): Columns {
        return Columns(table, *columnNames)
    }


}