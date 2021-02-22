package ru.nehodov.sqotlin.select

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.aggregateFunctions.AVG
import ru.nehodov.sqotlin.extensions.*

class SELECT_Test {
    private val table_name = "table_name"
    private val column_a = "column_a"
    private val column_b = "column_b"
    private val table_alias = "table_alias"
    private val alias_a = "alias_a"
    private val alias_b = "alias_b"

    @Test
    fun `when SELECT expression`() {
        val expect = """
            |SELECT
            |   a + a
        """.trimMargin("|")

        val actual =
                SELECT(
                        "a + a"
                ).sql()

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
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT one column FROM one table`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $table_name
            """.trimMargin("|")

        val actual =
                SELECT(
                        column_a
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT DISTINCT one column FROM one table`() {
        val expect = """
            |SELECT DISTINCT
            |   $column_a
        """.trimMargin("|")

        val actual =
                SELECT_DISTINCT(
                        column_a
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table`() {
        val expect = """
            |SELECT
            |   $column_a,
            |   $column_b
            |FROM
            |   $table_name
        """.trimMargin("|")

        val actual =
                SELECT(
                        column_a,
                        column_b
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT DISTINCT two columns FROM one table`() {
        val expect = """
            |SELECT DISTINCT
            |   $column_a,
            |   $column_b
            |FROM
            |   $table_name
        """.trimMargin("|")

        val actual =
                SELECT_DISTINCT(
                        column_a,
                        column_b
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT one column FROM one table and WHERE clause`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $table_name
            |WHERE
            |   $column_a = 3
        """.trimMargin("|")

        val actual =
                SELECT(
                        column_a
                ).FROM(
                        table_name
                ).WHERE(
                        column_a EQ 3
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT one column FROM one table with String alias`() {
        val expect = """
            |SELECT
            |   $column_a AS alias_a
            |FROM
            |   $table_name
            """.trimMargin("|")

        val actual =
                SELECT(
                        column_a AS "alias_a"
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT one column FROM one table with const alias`() {
        val expect = """
            |SELECT
            |   $column_a AS alias_a
            |FROM
            |   $table_name
            """.trimMargin("|")

        val actual =
                SELECT(
                        column_a AS alias_a
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table and one column alias`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b
            |FROM
            |   $table_name
        """.trimMargin("|")

        val actual =
                SELECT(
                        column_a AS alias_a,
                        column_b
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table and two column aliases`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b AS $alias_b
            |FROM
            |   $table_name
        """.trimMargin("|")

        val actual =
                SELECT(
                        column_a AS alias_a,
                        column_b AS alias_b
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table & two column aliases & table alias`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b AS $alias_b
            |FROM
            |   $table_name AS $table_alias
        """.trimMargin("|")

        val actual =
                SELECT(
                        column_a AS alias_a,
                        column_b AS alias_b
                ).FROM(
                        table_name AS table_alias
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT all columns`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   $table_name
        """.trimMargin("|")

        val actual =
                SELECT(
                        ALL
                ).FROM(
                        table_name
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT any string AS alias`() {
        val expect = """
            |SELECT
            |   0 AS $alias_a
            |FROM
            |   $table_name
        """.trimMargin("|")

        val actual =
                SELECT(
                        "0" AS alias_a
                ).FROM(
                        table_name
                ).sql()

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
                ).sql()

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
                ).sql()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $table_name
            |ORDER BY 
            |   $column_a
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                table_name
            ).ORDER_BY(
                column_a
            ).sql()

        assertEquals(expext, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY ASC`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $table_name
            |ORDER BY 
            |   $column_a ASC
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                table_name
            ).ORDER_BY(
                column_a.ASC()
            ).sql()

        assertEquals(expext, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY DESC`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $table_name
            |ORDER BY 
            |   $column_a DESC
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                table_name
            ).ORDER_BY(
                column_a.DESC()
            ).sql()

        assertEquals(expext, actual)
    }

    @Test
    fun `when SELECT FROM ORDER BY column_a DESC & column_b ASC`() {
        val expext = """
            |SELECT
            |   $column_a
            |FROM
            |   $table_name
            |ORDER BY 
            |   $column_a DESC,
            |   $column_b ASC
        """.trimMargin()

        val actual =
            SELECT(
                column_a
            ).FROM(
                table_name
            ).ORDER_BY(
                column_a.DESC(),
                column_b.ASC()
            ).sql()

        assertEquals(expext, actual)
    }
}