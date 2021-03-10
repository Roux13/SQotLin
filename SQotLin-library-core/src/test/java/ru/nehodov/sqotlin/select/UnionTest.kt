package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.TestDbSchemaConst.second_table
import ru.nehodov.sqotlin.TestDbSchemaConst.third_table
import ru.nehodov.sqotlin.extensions.UNION
import ru.nehodov.sqotlin.extensions.UNION_ALL

class UnionTest {

    @Test
    fun `when UNION 2 subQueries`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   SELECT
            |      $column_a
            |   FROM
            |      $first_table
            |   UNION
            |   SELECT
            |      $column_b
            |   FROM
            |      $second_table
        """.trimMargin()

        val subQuery1 =
            SELECT(
                column_a
            ).FROM(
                first_table
            )
        val subQuery2 =
            SELECT(
                column_b
            ).FROM(
                second_table
            )

        val actual =
            SELECT(
                ALL
            ).FROM(
                subQuery1 UNION subQuery2
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when UNION 3 subQueries`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   SELECT
            |      $column_a
            |   FROM
            |      $first_table
            |   UNION
            |   SELECT
            |      $column_b
            |   FROM
            |      $second_table
            |   UNION
            |   SELECT
            |      $column_c
            |   FROM
            |      $third_table
        """.trimMargin()

        val subQuery1 =
            SELECT(
                column_a
            ).FROM(
                first_table
            )
        val subQuery2 =
            SELECT(
                column_b
            ).FROM(
                second_table
            )
        val subQuery3 =
            SELECT(
                column_c
            ).FROM(
                third_table
            )

        val actual =
            SELECT(
                ALL
            ).FROM(
                subQuery1 UNION subQuery2 UNION subQuery3
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when UNION ALL 2 subQueries`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   SELECT
            |      $column_a
            |   FROM
            |      $first_table
            |   UNION ALL
            |   SELECT
            |      $column_b
            |   FROM
            |      $second_table
        """.trimMargin()

        val subQuery1 =
            SELECT(
                column_a
            ).FROM(
                first_table
            )
        val subQuery2 =
            SELECT(
                column_b
            ).FROM(
                second_table
            )

        val actual =
            SELECT(
                ALL
            ).FROM(
                subQuery1 UNION_ALL subQuery2
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when UNION ALL 3 subQueries`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   SELECT
            |      $column_a
            |   FROM
            |      $first_table
            |   UNION ALL
            |   SELECT
            |      $column_b
            |   FROM
            |      $second_table
            |   UNION ALL
            |   SELECT
            |      $column_c
            |   FROM
            |      $third_table
        """.trimMargin()

        val subQuery1 =
            SELECT(
                column_a
            ).FROM(
                first_table
            )
        val subQuery2 =
            SELECT(
                column_b
            ).FROM(
                second_table
            )
        val subQuery3 =
            SELECT(
                column_c
            ).FROM(
                third_table
            )

        val actual =
            SELECT(
                ALL
            ).FROM(
                subQuery1 UNION_ALL subQuery2 UNION_ALL subQuery3
            ).query()

        Assert.assertEquals(expect, actual)
    }
}