package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.select.SELECT

class IsNullTest {

    @Test
    fun `when just IS NULL`() {
        val expect = """
            |$column_a IS NULL
        """.trimMargin()

        val actual = column_a.IS_NULL()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just IS NOT NULL`() {
        val expect = """
            |$column_a IS NOT NULL
        """.trimMargin()

        val actual = column_a.IS_NOT_NULL()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IS NULL in WHERE clause `() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a IS NULL
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                column_a.IS_NULL()
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IS NOT NULL in WHERE clause `() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a IS NOT NULL
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                column_a.IS_NOT_NULL()
            ).query()

        Assert.assertEquals(expect, actual)
    }
}