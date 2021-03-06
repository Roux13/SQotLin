package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.extensions.AS

class SubQuerySelectTest {

    @Test
    fun `when SELECT with subQuery SELECT All`() {
        val expect = """
            |SELECT
            |   (
            |   SELECT
            |      *
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                SELECT(
                    ALL
                ).subQuery()
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with subQuery SELECT 1 column`() {
        val expect = """
            |SELECT
            |   (
            |   SELECT
            |      $column_a
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                SELECT(
                    column_a
                ).subQuery()
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with subQuery SELECT 2 columns`() {
        val expect = """
            |SELECT
            |   (
            |   SELECT
            |      $column_a,
            |      $column_b
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                SELECT(
                    column_a,
                    column_b
                ).subQuery()
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with subQuery SELECT 3 columns`() {
        val expect = """
            |SELECT
            |   (
            |   SELECT
            |      $column_a,
            |      $column_b,
            |      $column_c
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                SELECT(
                    column_a,
                    column_b,
                    column_c
                ).subQuery()
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with subQuery SELECT 3 columns with aliases`() {
        val expect = """
            |SELECT
            |   (
            |   SELECT
            |      $column_a AS a,
            |      $column_b AS b,
            |      $column_c AS c
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                SELECT(
                    column_a AS "a",
                    column_b AS "b",
                    column_c AS "c"
                ).subQuery()
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with subQuery SELECT 1 column from table`() {
        val expect = """
            |SELECT
            |   (
            |   SELECT
            |      $column_a
            |   FROM
            |      $first_table
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                SELECT(
                    column_a
                ).FROM(first_table
                ).subQuery()
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with 1 column & subQuery SELECT 1 column from table`() {
        val expect = """
            |SELECT
            |   $column_b,
            |   (
            |   SELECT
            |      $column_a
            |   FROM
            |      $first_table
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                column_b,
                SELECT(
                    column_a
                ).FROM(first_table
                ).subQuery()
            ).query()

        Assert.assertEquals(expect, actual)
    }
}