package ru.nehodov.sqotlin.insert

class Columns(table: String, vararg columns: String) {

    private val insertQuery = InsertQuery(table)

    init {
        insertQuery.setColumns(*columns)
    }

    fun VALUES(vararg values: Any): String {
        insertQuery.setValues(*values)
        return insertQuery.query()
    }

}