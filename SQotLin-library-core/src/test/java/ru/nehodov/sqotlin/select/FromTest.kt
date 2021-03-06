package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table_alias
import ru.nehodov.sqotlin.extensions.AS

class FromTest {

    @Test
    fun `when SELECT one column FROM one table`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).query()

        Assert.assertEquals(expect, actual)
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
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table`() {
        val expect = """
            |SELECT
            |   $column_a,
            |   $column_b
            |FROM
            |   $first_table
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a,
                column_b
            ).FROM(
                first_table
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT DISTINCT two columns FROM one table`() {
        val expect = """
            |SELECT DISTINCT
            |   $column_a,
            |   $column_b
            |FROM
            |   $first_table
        """.trimMargin("|")

        val actual =
            SELECT_DISTINCT(
                column_a,
                column_b
            ).FROM(
                first_table
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT one column FROM one table with String alias`() {
        val expect = """
            |SELECT
            |   $column_a AS alias_a
            |FROM
            |   $first_table
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a AS "alias_a"
            ).FROM(
                first_table
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT one column FROM one table with const alias`() {
        val expect = """
            |SELECT
            |   $column_a AS alias_a
            |FROM
            |   $first_table
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a AS alias_a
            ).FROM(
                first_table
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table and one column alias`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b
            |FROM
            |   $first_table
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a AS alias_a,
                column_b
            ).FROM(
                first_table
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table and two column aliases`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b AS $alias_b
            |FROM
            |   $first_table
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a AS alias_a,
                column_b AS alias_b
            ).FROM(
                first_table
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT two columns FROM one table & two column aliases & table alias`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b AS $alias_b
            |FROM
            |   $first_table AS $first_table_alias
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a AS alias_a,
                column_b AS alias_b
            ).FROM(
                first_table AS first_table_alias
            ).query()

        Assert.assertEquals(expect, actual)
    }
}