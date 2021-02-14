package ru.nehodov.sqotlin.aggregateFunctions

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS

class SUMTest {

    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when SUM without alias`() {
        val expect = "SUM($column_a)"

        val actual = SUM(column_a).AS_IS()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SUM with alias`() {
        val expect = "SUM($column_a) AS $alias_a"

        val actual = SUM(column_a) AS alias_a

        assertEquals(expect, actual)
    }
}