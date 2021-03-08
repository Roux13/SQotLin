package ru.nehodov.sqotlin.common

class CASE(private val caseExpression: Any = "") {

    fun WHEN(whenExpression: Any, then: Any): When {
        return When(
            whenExpression.toString(),
            then.toString(),
            CaseContainer(caseExpression.toString())
        )
    }
}