package ru.nehodov.sqotlin.aggregateFunctions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS

class COUNT_Test {

    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when COUNT without alias`() {
        val expect = "COUNT($column_a)"

        val actual = COUNT(column_a).AS_IS()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when COUNT with alias`() {
        val expect = "COUNT($column_a) AS $alias_a"

        val actual = COUNT(column_a) AS alias_a

        Assert.assertEquals(expect, actual)
    }

}