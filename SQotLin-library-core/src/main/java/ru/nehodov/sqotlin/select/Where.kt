package ru.nehodov.sqotlin.select

class Where(private val FROM: From, private val rowFilter: String) {
    fun build() = """
        |${FROM.build()}
        |$WHERE
        |   $rowFilter
        """.trimMargin()

    companion object {
        const val WHERE = "WHERE"
    }
}