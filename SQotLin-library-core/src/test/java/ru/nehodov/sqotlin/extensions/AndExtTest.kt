package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c

class AndExtTest {

    @Test
    fun `when simple AND`() {
        val expect = """
            |$column_a = 0
            |   AND $column_b > 0
        """.trimMargin()

        val actual = column_a EQ 0 AND column_b GREATER 0

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when double AND`() {
        val expect = """
            |$column_a = 0
            |   AND $column_b > 0
            |   AND $column_c != 0
        """.trimMargin()

        val actual =
            column_a EQ 0 AND column_b GREATER 0 AND column_c NEQ 0

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when AND & OR`() {
        val expect = """
            |$column_a = 0
            |   AND ($column_b > 0 OR $column_c != 0)
        """.trimMargin()

        val actual =
            (column_a EQ 0) AND ((column_b GREATER 0) OR (column_c NEQ 0))

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when second AND by if condition true`() {
        val condition = true
        val expect = """
            |$column_a = 0
            |   AND $column_b > 0
            |   ${if(condition) "AND $column_c != 0" else ""}
        """.trimMargin()

        val actual = column_a EQ 0 AND column_b GREATER 0 AND (if(condition) column_c NEQ 0 else "")

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when second AND by if condition false`() {
        val condition = false
        val expect = """
            |$column_a = 0
            |   AND $column_b > 0
            ${if(condition) "|   AND $column_c != 0" else ""}""".trimMargin()

        val actual = (column_a EQ 0) AND (column_b GREATER 0) AND (if(condition) column_c NEQ 0 else "")

        Assert.assertEquals(expect, actual)
    }
}