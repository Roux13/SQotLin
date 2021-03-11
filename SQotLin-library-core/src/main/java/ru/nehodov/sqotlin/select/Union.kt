package ru.nehodov.sqotlin.select

class Union(val subQuery: String): ISelect {

    override fun query(): String {
        return subQuery.trimMargin()
    }

    override fun subQuery(): String {
        return """
            |(${subQuery.trimEnd()}
            |)
        """
    }
}