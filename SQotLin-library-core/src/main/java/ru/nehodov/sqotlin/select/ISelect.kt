package ru.nehodov.sqotlin.select

interface ISelect {

    fun query(): String

    fun subQuery(): String
}