package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.extensions.EQ

class WhereTest {

    @Test
    fun `when SELECT one column FROM one table and WHERE clause`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a = 3
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                column_a EQ 3
            ).query()

        Assert.assertEquals(expect, actual)
    }

}