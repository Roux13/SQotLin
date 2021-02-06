package ru.nehodov.sqotlin.select

class Where(FROM: ISelect, rowFilter: String) : ISelect {

    private var query: String

    init {
        query = """
        |${FROM.sql()}
        |$WHERE
        |   $rowFilter
        """.trimMargin()
    }

    override fun sql() = query

    companion object {
        const val WHERE = "WHERE"
    }
}