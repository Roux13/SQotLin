package ru.nehodov.sqotlin.aggregateFunctions

import org.junit.Assert
import org.junit.Test

class AVG_Test {

    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when AVG without alias`() {
        val expect = "AVG($column_a)"

        val actual = AVG(column_a).AS_IS()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when AVG with alias`() {
        val expect = "AVG($column_a) AS $alias_a"

        val actual = AVG(column_a) AS alias_a

        Assert.assertEquals(expect, actual)
    }

}