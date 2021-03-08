package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.select.SELECT

class LikeTest {

    @Test
    fun `when just LIKE`() {
        val pattern = "%pattern"
        val expect = """
            |$column_a LIKE '${pattern}'
        """.trimMargin()

        val actual = column_a LIKE pattern

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just LIKE & pattern surrounded in single quotes `() {
        val expect = """
            |$column_a LIKE '%pattern'
        """.trimMargin()

        val actual = column_a LIKE "'%pattern'"

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when LIKE in WHERE clause `() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a LIKE '%pattern%'
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                column_a LIKE "%pattern%"
            ).query()

        Assert.assertEquals(expect, actual)
    }
}