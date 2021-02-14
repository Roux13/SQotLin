package ru.nehodov.sqotlin.select

import ru.nehodov.sqotlin.aggregateFunctions.AggregateFunc
import ru.nehodov.sqotlin.extensions.AS_IS

class IFNULL(
        val checked: String,
        val default: String
) {

    constructor(
            groupFun: AggregateFunc,
            default: String
    ) : this(groupFun.AS_IS(), default)

    constructor(
            groupFun: AggregateFunc,
            default: Any
    ) : this(groupFun.AS_IS(), default.toString())

    constructor(
            checked: String,
            default: Any
    ) : this(checked, default.toString())

}


