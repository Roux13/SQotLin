package ru.nehodov.sqotlin.common

class CaseContainer(private val caseExpression: String = "") {

    private val whenClauses: MutableList<When> = mutableListOf()
    private var elseClause: Else? = null

    fun addWhen(whenClause: When) {
        whenClauses.add(whenClause)
    }

    fun addElse(elseClause: Else) {
        this.elseClause = elseClause
    }

    fun build(): String {
        val _case = StringBuilder(
            """
            |CASE${if (caseExpression.isNotEmpty()) " $caseExpression" else ""}
        """
        )
        whenClauses.forEach { whenClause ->
            _case.append("|     WHEN ${whenClause.whenExpression} THEN ${whenClause.then}\n")
        }
        elseClause?.let { _case.append("|     ELSE ${elseClause?.elseResult}\n") }
        _case.append("|END")
        return _case.toString().trimMargin()
    }
}