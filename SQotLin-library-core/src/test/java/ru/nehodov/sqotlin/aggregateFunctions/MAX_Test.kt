package ru.nehodov.sqotlin.aggregateFunctions

import org.junit.Assert
import org.junit.Test

class MAX_Test {

    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when MAX without alias`() {
        val expect = "MAX($column_a)"

        val actual = MAX(column_a).AS_IS()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when MAX with alias`() {
        val expect = "MAX($column_a) AS $alias_a"

        val actual = MAX(column_a) AS alias_a

        Assert.assertEquals(expect, actual)
    }

}