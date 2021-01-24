package ru.nehodov.sqotlin.select

class FROM(private val query: String) {

    fun WHERE(clause: String): WHERE {
        return WHERE(
                query = """
                |$query
                |$WHERE
                |   $clause
                """.trimMargin()
        )
    }

    fun build() = query

    companion object {
        private const val WHERE = "WHERE"
    }
}