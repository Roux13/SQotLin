package ru.nehodov.sqotlin.select

import ru.nehodov.sqotlin.Aliasable
import ru.nehodov.sqotlin.SQLiteConst.EMPTY
import ru.nehodov.sqotlin.SqlComparable
import ru.nehodov.sqotlin.aggregateFunctions.AggregateFunc

class IFNULL(
        val checked: String,
        val default: String = EMPTY,
) : SqlComparable, Aliasable {

    constructor(
        groupFun: AggregateFunc,
        default: String
    ) : this(groupFun.toString(), default)

    constructor(
        groupFun: AggregateFunc,
        default: Any
    ) : this(groupFun.toString(), default.toString())

    constructor(
        checked: String,
        default: Any
    ) : this(checked, default.toString())

    override fun toString(): String {
        return """
            |$IFNULL($checked, ${if (default.isEmpty()) EMPTY else default}
        """.trimMargin("|")
    }

    companion object {
        private const val IFNULL = "IFNULL"
    }
}

