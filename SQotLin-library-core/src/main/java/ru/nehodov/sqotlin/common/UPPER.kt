package ru.nehodov.sqotlin.common

class UPPER(private val text: String) {

    var clause = ""

    init {
        clause = "UPPER($text)"
    }


    override fun toString(): String {
        return clause
    }
}