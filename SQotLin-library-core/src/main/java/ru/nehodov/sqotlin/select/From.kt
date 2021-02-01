package ru.nehodov.sqotlin.select

class From(select: SELECT, vararg tableList: String) {

    private var query: String

    init {
        query = """
            |${select.build()}
            |$FROM
        """.trimMargin()
        for (i in tableList.indices) {
            query =
                """|$query
                   |   ${tableList[i]},
                   """.trimMargin("|")

        }
        query = query.trimEnd(',')
    }

    fun WHERE(rowFilter: String): Where {
        return Where(this, rowFilter)
    }

    fun build() = query

    companion object {
        private const val FROM = "FROM"
    }
}