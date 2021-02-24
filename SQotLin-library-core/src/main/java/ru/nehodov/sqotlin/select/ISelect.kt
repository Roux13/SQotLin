package ru.nehodov.sqotlin.select

interface ISelect {

    fun sql(): String

    fun subQuery(): String
}