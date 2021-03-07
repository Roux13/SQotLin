package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.select.SELECT

class InTest {

    @Test
    fun `when just IN 1 value`() {
        val expect = """
            |$column_a IN (1)
        """.trimMargin()

        val actual = column_a.IN(1)

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just IN 2 values`() {
        val expect = """
            |$column_a IN (1, 2)
        """.trimMargin()

        val actual = column_a.IN(1, 2)

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just IN subQuery`() {
        val expect = """
            |$column_a IN (
            |            SELECT
            |               *
            |            FROM
            |               $first_table
            |            )
        """.trimMargin()

        val actual =
            column_a.IN(
            SELECT(ALL).FROM(first_table)
        )

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just NOT IN 1 value`() {
        val expect = """
            |$column_a NOT IN (1)
        """.trimMargin()

        val actual = column_a.NOT_IN(1)

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just NOT IN 2 values`() {
        val expect = """
            |$column_a NOT IN (1, 2)
        """.trimMargin()

        val actual = column_a.NOT_IN(1, 2)

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just NOT IN subQuery`() {
        val expect = """
            |$column_a NOT IN (
            |                SELECT
            |                   *
            |                FROM
            |                   $first_table
            |                )
        """.trimMargin()

        val actual =
            column_a.NOT_IN(
                SELECT(ALL).FROM(first_table)
            )

        Assert.assertEquals(expect, actual)
    }
}