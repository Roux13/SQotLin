package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.column_d
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.aggregateFunctions.SUM
import ru.nehodov.sqotlin.extensions.AS

class GroupByTest {

    @Test
    fun `when simple GROUP BY`() {
        val expect = """
            |SELECT
            |   SUM($column_a) AS $alias_a
            |FROM
            |   $first_table
            |GROUP BY
            |   $column_b
        """.trimMargin()

        val actual =
            SELECT(
                SUM(column_a) AS alias_a
            ).FROM(
                first_table
            ).GROUP_BY(
                column_b
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple GROUP BY 2 columns`() {
        val expect = """
            |SELECT
            |   SUM($column_a) AS $alias_a
            |FROM
            |   $first_table
            |GROUP BY
            |   $column_b,
            |   $column_c
        """.trimMargin()

        val actual =
            SELECT(
                SUM(column_a) AS alias_a
            ).FROM(
                first_table
            ).GROUP_BY(
                column_b,
                column_c,
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple GROUP BY 2 columns & one of them by condition & if condition is true`() {
        val condition = true
        val expect = """
            |SELECT
            |   SUM($column_a) AS $alias_a
            |FROM
            |   $first_table
            |GROUP BY
            |   $column_b,
            |   $column_c
        """.trimMargin()

        val actual =
            SELECT(
                SUM(column_a) AS alias_a
            ).FROM(
                first_table
            ).GROUP_BY(
                column_b,
                if (condition) column_c else "",
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple GROUP BY 2 columns & one of them by condition & if condition is false`() {
        val condition = false
        val expect = """
            |SELECT
            |   SUM($column_a) AS $alias_a
            |FROM
            |   $first_table
            |GROUP BY
            |   $column_b
        """.trimMargin()

        val actual =
            SELECT(
                SUM(column_a) AS alias_a
            ).FROM(
                first_table
            ).GROUP_BY(
                column_b,
                if (condition) column_c else "",
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple GROUP BY 3 columns & one of them by condition & if condition is false`() {
        val condition = false
        val expect = """
            |SELECT
            |   SUM($column_a) AS $alias_a
            |FROM
            |   $first_table
            |GROUP BY
            |   $column_b,
            |   $column_d
        """.trimMargin()

        val actual =
            SELECT(
                SUM(column_a) AS alias_a
            ).FROM(
                first_table
            ).GROUP_BY(
                column_b,
                if (condition) column_c else "",
                column_d
            ).query()

        Assert.assertEquals(expect, actual)
    }
}