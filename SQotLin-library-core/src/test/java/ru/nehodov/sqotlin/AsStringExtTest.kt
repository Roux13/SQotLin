package ru.nehodov.sqotlin

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.extensions.AS

class AsStringExtTest {

    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when alias is simple String`() {
        val expect = """
            |$column_a AS alias_a
        """.trimMargin("|")

        val actual = column_a AS "alias_a"

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when alias is const`() {
        val expect = """
            |$column_a AS alias_a
        """.trimMargin("|")

        val actual = column_a AS alias_a

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when AS is empty then alias is absent`() {
        val expect = """
            |$column_a
        """.trimMargin("|")

        val actual = column_a AS ""

        Assert.assertEquals(expect, actual)
    }
}