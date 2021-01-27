package ru.nehodov.sqotlin.aggregateFunctions

class SUM(arg: String) : AggregateFunc(arg) {
    override val funcName: String = "SUM"
}