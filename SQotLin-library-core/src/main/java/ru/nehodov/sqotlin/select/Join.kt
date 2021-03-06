package ru.nehodov.sqotlin.select

open class Join(
    private val query: SelectQuery,
    private val joinType: String,
    private val table: String
    ) {

    infix fun ON(joinCondition: String): JoinOn {
        return JoinOn(query, joinType, table, joinCondition)
    }
}