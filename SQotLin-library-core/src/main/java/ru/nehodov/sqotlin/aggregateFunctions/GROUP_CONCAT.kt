package ru.nehodov.sqotlin.aggregateFunctions

class GROUP_CONCAT(arg: String, separator: String = ""): AggregateFunc(
    "$arg${if(separator.isNotEmpty()) ", '$separator'" else ""}"
) {
    override val funcName: String
        get() = "GROUP CONCAT"
}