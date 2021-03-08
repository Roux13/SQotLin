package ru.nehodov.sqotlin.common

class When(
    val whenExpression: String,
    val then: String,
    private val caseContainer: CaseContainer
    ) {

    init {
        caseContainer.addWhen(this)
    }

    fun WHEN(whenExpression: Any, then: Any): When {
        return When(whenExpression.toString(), then.toString(), caseContainer)
    }

    fun ELSE(elseResult: Any): Else{
        return Else(elseResult.toString(), caseContainer)
    }

    fun END(): String {
        return caseContainer.build()
    }

}