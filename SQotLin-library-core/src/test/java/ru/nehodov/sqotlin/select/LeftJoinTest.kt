package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst
import ru.nehodov.sqotlin.TestDbSchemaConst
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table_alias
import ru.nehodov.sqotlin.TestDbSchemaConst.second_table
import ru.nehodov.sqotlin.TestDbSchemaConst.second_table_alias
import ru.nehodov.sqotlin.extensions.AND
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.EQ
import ru.nehodov.sqotlin.extensions.OR

class LeftJoinTest {

    @Test
    fun `when 1 LEFT JOIN`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |LEFT JOIN $second_table
            |   ON $column_a = $column_b
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).LEFT_JOIN(second_table).ON(
                column_a EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 LEFT JOIN with 2 join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |LEFT JOIN $second_table
            |   ON $column_a = $column_b
            |   AND $column_b = $column_c
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).LEFT_JOIN(second_table).ON(
                column_a EQ column_b
                        AND column_b EQ column_c
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 LEFT JOIN with AND & OR join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |LEFT JOIN $second_table
            |   ON $column_a = $column_b
            |   AND ($column_b = $column_c OR $column_a = $column_c)
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).LEFT_JOIN(second_table).ON(
                column_a EQ column_b
                        AND ((column_b EQ column_c) OR (column_a EQ column_c))
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 LEFT JOIN with aliases`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |LEFT JOIN $second_table AS $second_table_alias
            |   ON $column_a = $column_b
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).LEFT_JOIN(second_table AS second_table_alias).ON(
                column_a EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 LEFT JOIN with with aliases & AND & OR join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |LEFT JOIN $second_table AS $second_table_alias
            |   ON $column_a = $column_b
            |   AND ($column_b = $column_c OR $column_a = $column_c)
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).LEFT_JOIN(
                second_table AS second_table_alias
            ).ON(
                column_a EQ column_b
                        AND ((column_b EQ column_c) OR (column_a EQ column_c))
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 LEFT JOIN subQuery`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |LEFT JOIN (
            |   SELECT
            |      *
            |   FROM
            |      $second_table
            |   )
            |   ON $column_a = $column_b
            """.trimMargin("|")

        val subQuery = SELECT(
            SQLiteConst.ALL
        ).FROM(
            second_table
        )

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).LEFT_JOIN(subQuery).ON(
                column_a EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 LEFT JOIN subQuery with aliases & join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |LEFT JOIN (
            |   SELECT
            |      *
            |   FROM
            |      $second_table
            |   ) AS $second_table_alias
            |   ON $column_a = $column_b
            |   AND ($column_b = $column_c OR $column_a = $column_c)
            """.trimMargin("|")

        val subQuery = SELECT(
            SQLiteConst.ALL
        ).FROM(
            second_table
        )

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).LEFT_JOIN(subQuery AS second_table_alias).ON(
                column_a EQ column_b
                        AND ((column_b EQ column_c) OR (column_a EQ column_c))
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 2 LEFT JOINs with aliases`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |LEFT JOIN $second_table AS $second_table_alias
            |   ON $column_a = $column_b
            |LEFT JOIN ${TestDbSchemaConst.third_table} AS ${TestDbSchemaConst.third_table_alias}
            |   ON $column_c = $column_b
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).LEFT_JOIN(second_table AS second_table_alias).ON(
                column_a EQ column_b
            ).LEFT_JOIN(TestDbSchemaConst.third_table AS TestDbSchemaConst.third_table_alias).ON(
                column_c EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }
}