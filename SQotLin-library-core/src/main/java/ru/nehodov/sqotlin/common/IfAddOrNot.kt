package ru.nehodov.sqotlin.common

inline fun <T> T.addIfOrNull(predicate: (T) -> Boolean): T? {

    return if (predicate(this)) {
        this
    } else {
        null
    }
}

//fun addIf(condition: Boolean, add: (Any) -> String): String {
//
//    return if (predicate(this)) {
//        this.toString()
//    } else {
//        ""
//    }
//}

//
//public inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? {
//    contract {
//        callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
//    }
//    return if (predicate(this)) this else null
//}