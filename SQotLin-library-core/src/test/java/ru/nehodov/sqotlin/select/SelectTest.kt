package ru.nehodov.sqotlin.select

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.aggregateFunctions.AVG
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.ASC
import ru.nehodov.sqotlin.extensions.AS_IS
import ru.nehodov.sqotlin.extensions.DESC

class SelectTest {

    @Test
    fun `when SELECT expression`() {
        val expect = """
            |SELECT
            |   a + a
        """.trimMargin("|")

        val actual =
                SELECT(
                        "a + a"
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT DISTINCT expression`() {
        val expect = """
            |SELECT DISTINCT
            |   a + a
        """.trimMargin("|")

        val actual =
                SELECT_DISTINCT(
                        "a + a"
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT all columns`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   $first_table
        """.trimMargin("|")

        val actual =
                SELECT(
                        ALL
                ).FROM(
                        first_table
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT any string AS alias`() {
        val expect = """
            |SELECT
            |   0 AS $alias_a
            |FROM
            |   $first_table
        """.trimMargin("|")

        val actual =
                SELECT(
                        "0" AS alias_a
                ).FROM(
                        first_table
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with AVG without alias`() {
        val expect = """
            |SELECT
            |   AVG($column_a)
        """.trimMargin()

        val actual =
                SELECT(
                    AVG(column_a).AS_IS()
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with AVG with alias`() {
        val expect = """
            |SELECT
            |   AVG($column_a) AS $alias_a
        """.trimMargin()

        val actual =
                SELECT(
                        AVG(column_a) AS alias_a
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |ORDER BY 
            |   $column_a
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).ORDER_BY(
                column_a
            ).query()

        assertEquals(expext, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY ASC`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |ORDER BY 
            |   $column_a ASC
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).ORDER_BY(
                column_a.ASC()
            ).query()

        assertEquals(expext, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY DESC`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |ORDER BY 
            |   $column_a DESC
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).ORDER_BY(
                column_a.DESC()
            ).query()

        assertEquals(expext, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY column_a DESC & column_b ASC`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |ORDER BY 
            |   $column_a DESC,
            |   $column_b ASC
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).ORDER_BY(
                column_a.DESC(),
                column_b.ASC()
            ).query()

        assertEquals(expext, actual)
    }
}