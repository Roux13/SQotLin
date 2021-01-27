package ru.nehodov.sqotlin.aggregateFunctions

class MAX(arg: String): AggregateFunc(arg) {
    override val funcName: String = "MAX"
}