package ru.nehodov.sqotlin.aggregateFunctions

import ru.nehodov.sqotlin.Aliasable

abstract class AggregateFunc(val arg: String) : Aliasable {

    abstract val funcName: String

    override fun toString(): String {
        return "${this.funcName}(${this.arg})"
    }
}