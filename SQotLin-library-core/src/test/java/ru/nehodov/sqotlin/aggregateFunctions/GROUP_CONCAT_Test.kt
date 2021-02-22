package ru.nehodov.sqotlin.aggregateFunctions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS

class GROUP_CONCAT_Test {
    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when group concat without separator as is`() {
        val expect = """
            |GROUP CONCAT($column_a)
        """.trimMargin()

        val actual = GROUP_CONCAT(column_a).AS_IS()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when group concat with separator as is`() {
        val expect = """
            |GROUP CONCAT($column_a, ';')
        """.trimMargin()

        val actual = GROUP_CONCAT(column_a, ";").AS_IS()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when group concat without separator with alias`() {
        val expect = """
            |GROUP CONCAT($column_a) AS $alias_a
        """.trimMargin()

        val actual = GROUP_CONCAT(column_a) AS alias_a

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when group concat with separator with alias`() {
        val expect = """
            |GROUP CONCAT($column_a, ';') AS $alias_a
        """.trimMargin()

        val actual = GROUP_CONCAT(column_a, ";") AS alias_a

        Assert.assertEquals(expect, actual)
    }
}