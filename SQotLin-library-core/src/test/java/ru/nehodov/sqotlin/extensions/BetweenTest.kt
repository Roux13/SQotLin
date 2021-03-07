package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.select.SELECT

class BetweenTest {

    @Test
    fun `when just BETWEEN`() {
        val expect = """
            |$column_a BETWEEN 0 AND 10
        """.trimMargin("|")

        val actual = column_a BETWEEN 0 AND 10

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple BETWEEN`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a BETWEEN 0 AND 10
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                column_a BETWEEN 0 AND 10
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just NOT BETWEEN`() {
        val expect = """
            |$column_a NOT BETWEEN 0 AND 10
        """.trimMargin("|")

        val actual = column_a NOT_BETWEEN 0 AND 10

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple NOT BETWEEN`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a NOT BETWEEN 0 AND 10
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                column_a NOT_BETWEEN 0 AND 10
            ).query()

        Assert.assertEquals(expect, actual)
    }

}