package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table_alias
import ru.nehodov.sqotlin.extensions.AS

class SubqueryFromTest {

    @Test
    fun `when FROM with subQuery SELECT All`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   (
            |   SELECT
            |      *
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                ALL
            ).FROM(
                SELECT(
                    ALL
                )
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when FROM with subQuery SELECT 1 column`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   (
            |   SELECT
            |      $column_a
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                ALL
            ).FROM(
                SELECT(
                    column_a
                )
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when FROM with subQuery SELECT 1 column with alias`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   (
            |   SELECT
            |      $column_a AS $alias_a
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                ALL
            ).FROM(
                SELECT(
                    column_a AS alias_a
                )
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when FROM with subQuery SELECT 2 columns`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   (
            |   SELECT
            |      $column_a,
            |      ${column_b}
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                ALL
            ).FROM(
                SELECT(
                    column_a,
                    column_b
                )
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when FROM with subQuery SELECT 2 columns with aliases`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   (
            |   SELECT
            |      $column_a AS $alias_a,
            |      $column_b AS $alias_b
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                ALL
            ).FROM(
                SELECT(
                    column_a AS alias_a,
                    column_b AS alias_b
                )
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when FROM with subQuery SELECT 2 columns with aliases & FROM`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   (
            |   SELECT
            |      $column_a AS $alias_a,
            |      $column_b AS $alias_b
            |   FROM
            |      $first_table
            |   )
        """.trimMargin("|")

        val actual =
            SELECT(
                ALL
            ).FROM(
                SELECT(
                    column_a AS alias_a,
                    column_b AS alias_b
                ).FROM(
                    first_table
                )
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when FROM with aliased subQuery SELECT 2 columns with aliases & FROM`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   (
            |   SELECT
            |      $column_a AS $alias_a,
            |      $column_b AS $alias_b
            |   FROM
            |      $first_table
            |   ) AS $first_table_alias
        """.trimMargin("|")

        val actual =
            SELECT(
                ALL
            ).FROM(
                SELECT(
                    column_a AS alias_a,
                    column_b AS alias_b
                ).FROM(
                    first_table
                ) AS first_table_alias
            ).query()

        Assert.assertEquals(expect, actual)
    }
}