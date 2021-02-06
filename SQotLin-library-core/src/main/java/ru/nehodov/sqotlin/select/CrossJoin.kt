package ru.nehodov.sqotlin.select

class CrossJoin(from: ISelect, table: String) : Join(from.sql(), CROSS_JOIN, table) {

    companion object {
        private const val CROSS_JOIN = "CROSS JOIN"
    }

}