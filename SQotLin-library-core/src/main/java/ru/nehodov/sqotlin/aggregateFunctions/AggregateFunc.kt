package ru.nehodov.sqotlin.aggregateFunctions

abstract class AggregateFunc(private val arg: String) {

    protected abstract val funcName: String

    private val AS = "AS"

    infix fun AS(alias: String): String {
        return "$funcName($arg)${takeAliasString(alias)}"
    }

    fun AS_IS(): String {
        return AS("")
    }

    private fun takeAliasString(alias: String): String {
        return if (alias.isNotEmpty()) {
            " $AS $alias"
        } else {
            ""
        }
    }
}