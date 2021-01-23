package ru.nehodov.sqotlin.select

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.EQ

class SELECT_Test {
    val table_name = "table_name"
    val column_a = "column_a"
    val column_b = "column_b"

    @Test
    fun `when select one column from one table`() {
        val expect = """
            |SELECT
            |   ${column_a}
            |FROM
            |   ${table_name}
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                table_name
            ).get()

        assertEquals(expect, actual)
    }

    @Test
    fun `when select two columns from one table`() {
        val expect = """
            |SELECT
            |   ${column_a},
            |   ${column_b}
            |FROM
            |   ${table_name}
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a,
                column_b
            ).FROM(
                table_name
            ).get()

        assertEquals(expect, actual)
    }

    @Test
    fun `when select one column from one table and where clause`() {
        val expect = """
            |SELECT
            |   ${column_a}
            |FROM
            |   ${table_name}
            |WHERE
            |   ${column_a} = 3
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                table_name
            ).WHERE(
                column_a EQ "3"
            ).get()

        assertEquals(expect, actual)
    }
}