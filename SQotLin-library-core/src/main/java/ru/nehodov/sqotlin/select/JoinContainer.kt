package ru.nehodov.sqotlin.select

data class JoinContainer(
    val joinType: String,
    val table: String,
    val joinCondition: String,
)