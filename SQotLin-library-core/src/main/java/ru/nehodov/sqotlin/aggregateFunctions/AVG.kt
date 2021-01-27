package ru.nehodov.sqotlin.aggregateFunctions

class AVG(arg: String): AggregateFunc(arg) {
    override val funcName: String = "AVG"
}