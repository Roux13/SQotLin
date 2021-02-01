package ru.nehodov.sqotlin.aggregateFunctions

import org.junit.Assert
import org.junit.Test

class MIN_Test {

    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when MIN without alias`() {
        val expect = "MIN($column_a)"

        val actual = MIN(column_a).AS_IS()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when MIN with alias`() {
        val expect = "MIN($column_a) AS $alias_a"

        val actual = MIN(column_a) AS alias_a

        Assert.assertEquals(expect, actual)
    }

}