package ru.nehodov.sqotlin.common

class Else(val elseResult: String, private val caseContainer: CaseContainer) {

    init {
        caseContainer.addElse(this)
    }

    fun END(): String {
        return caseContainer.build()
    }

}