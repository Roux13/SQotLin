package ru.nehodov.sqotlin.aggregateFunctions

abstract class AggregateFunc(val arg: String) {

    abstract val funcName: String
}