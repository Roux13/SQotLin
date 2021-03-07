package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.column_d
import ru.nehodov.sqotlin.TestDbSchemaConst.column_e
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.extensions.AND
import ru.nehodov.sqotlin.extensions.EQ
import ru.nehodov.sqotlin.extensions.NEQ

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

    @Test
    fun `when in WHERE 2 row filters`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a = 3
            |   AND $column_b != 0
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                (column_a EQ 3)
                        AND (column_b NEQ 0)
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when in WHERE 2 row filters & one of them by if condition & condition is true`() {
        val condition = true
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a = 3
            |   AND $column_b != 0
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                (column_a EQ 3)
                        AND (if (condition) column_b NEQ 0 else "")
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when in WHERE 2 row filters & one of them by if condition & condition is false`() {
        val condition = false
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
                (column_a EQ 3)
                        AND (if (condition) column_b NEQ 0 else "")
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when in WHERE 5 row filters & tree of them by if condition & condition is false`() {
        val condition = false
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |WHERE
            |   $column_a = 3
            |   AND $column_c = 0
            |   AND $column_e = 0
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).WHERE(
                (column_a EQ 3)
                        AND (if (condition) column_b NEQ 0 else "")
                        AND (column_c EQ 0)
                        AND (if (condition) column_d EQ 0 else "")
                        AND (column_e EQ 0)
            ).query()

        Assert.assertEquals(expect, actual)
    }

}