package ru.nehodov.sqotlin.select

class LeftJoin(from: ISelect, table: String) : Join(from.sql(), LEFT_JOIN, table) {

    companion object {
        private const val LEFT_JOIN = "LEFT JOIN"
    }
}