package ru.nehodov.sqotlin.aggregateFunctions

class COUNT(arg: String): AggregateFunc(arg) {
    override val funcName: String = "COUNT"
}