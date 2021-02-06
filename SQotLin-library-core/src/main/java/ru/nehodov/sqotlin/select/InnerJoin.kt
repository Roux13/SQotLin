package ru.nehodov.sqotlin.select

class InnerJoin(from: ISelect, table: String) : Join(from.sql(), INNER_JOIN, table) {

    companion object {
        private const val INNER_JOIN = "INNER JOIN"
    }

}