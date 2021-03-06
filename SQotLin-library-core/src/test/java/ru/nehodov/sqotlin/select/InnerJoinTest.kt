package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table_alias
import ru.nehodov.sqotlin.TestDbSchemaConst.second_table
import ru.nehodov.sqotlin.TestDbSchemaConst.second_table_alias
import ru.nehodov.sqotlin.TestDbSchemaConst.third_table
import ru.nehodov.sqotlin.TestDbSchemaConst.third_table_alias
import ru.nehodov.sqotlin.extensions.AND
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.EQ
import ru.nehodov.sqotlin.extensions.OR

class InnerJoinTest {

    @Test
    fun `when 1 INNER JOIN`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |INNER JOIN $second_table
            |   ON $column_a = $column_b
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).INNER_JOIN(second_table).ON(
                column_a EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 INNER JOIN with 2 join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |INNER JOIN $second_table
            |   ON $column_a = $column_b AND $column_b = $column_c
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).INNER_JOIN(second_table).ON(
                column_a EQ column_b
                        AND column_b EQ column_c
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 INNER JOIN with AND & OR join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |INNER JOIN $second_table
            |   ON $column_a = $column_b AND $column_b = $column_c OR $column_a = $column_c
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).INNER_JOIN(second_table).ON(
                column_a EQ column_b
                        AND (column_b EQ column_c OR column_a EQ column_c)
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 INNER JOIN with aliases`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |INNER JOIN $second_table AS $second_table_alias
            |   ON $column_a = $column_b
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).INNER_JOIN(second_table AS second_table_alias).ON(
                column_a EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 INNER JOIN with with aliases & AND & OR join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |INNER JOIN $second_table AS $second_table_alias
            |   ON $column_a = $column_b AND $column_b = $column_c OR $column_a = $column_c
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).INNER_JOIN(second_table AS second_table_alias).ON(
                column_a EQ column_b
                        AND (column_b EQ column_c OR column_a EQ column_c)
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 INNER JOIN subQuery`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table
            |INNER JOIN (
            |   SELECT
            |      *
            |   FROM
            |      $second_table
            |   )
            |   ON $column_a = $column_b
            """.trimMargin("|")

        val subQuery = SELECT(
            ALL
        ).FROM(
            second_table
        )

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table
            ).INNER_JOIN(subQuery).ON(
                column_a EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 1 INNER JOIN subQuery with aliases & join conditions`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |INNER JOIN (
            |   SELECT
            |      *
            |   FROM
            |      $second_table
            |   ) AS $second_table_alias
            |   ON $column_a = $column_b AND $column_b = $column_c OR $column_a = $column_c
            """.trimMargin("|")

        val subQuery = SELECT(
            ALL
        ).FROM(
            second_table
        )

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).INNER_JOIN(subQuery AS second_table_alias).ON(
                column_a EQ column_b
                        AND (column_b EQ column_c OR column_a EQ column_c)
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when 2 INNER JOINs with aliases`() {
        val expect = """
            |SELECT
            |   $column_a
            |FROM
            |   $first_table AS $first_table_alias
            |INNER JOIN $second_table AS $second_table_alias
            |   ON $column_a = $column_b
            |INNER JOIN $third_table AS $third_table_alias
            |   ON $column_c = $column_b
            """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).FROM(
                first_table AS first_table_alias
            ).INNER_JOIN(second_table AS second_table_alias).ON(
                column_a EQ column_b
            ).INNER_JOIN(third_table AS third_table_alias).ON(
                column_c EQ column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }
}