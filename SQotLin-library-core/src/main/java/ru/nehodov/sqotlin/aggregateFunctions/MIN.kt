package ru.nehodov.sqotlin.aggregateFunctions

class MIN(arg: String): AggregateFunc(arg) {
    override val funcName: String = "MIN"
}