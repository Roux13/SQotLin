package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.aggregateFunctions.COUNT
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS
import ru.nehodov.sqotlin.extensions.GREATER

class HavingTest {

    @Test
    fun `when simple HAVING`() {
        val expect = """
            |SELECT
            |   COUNT($column_a)
            |FROM
            |   $first_table
            |GROUP BY
            |   $column_b
            |HAVING
            |   COUNT($column_a) > 0
        """.trimMargin()

        val actual =
            SELECT(
                COUNT(column_a).AS_IS()
            ).FROM(
                first_table
            ).GROUP_BY(
                column_b
            ).HAVING(
                COUNT(column_a).AS_IS() GREATER 0
            ).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple HAVING with aliases`() {
        val expect = """
            |SELECT
            |   COUNT($column_a) AS $alias_a
            |FROM
            |   $first_table
            |GROUP BY
            |   $column_b
            |HAVING
            |   $alias_a > 0
        """.trimMargin()

        val actual =
            SELECT(
                COUNT(column_a) AS alias_a
            ).FROM(
                first_table
            ).GROUP_BY(
                column_b
            ).HAVING(
                alias_a GREATER 0
            ).query()

        Assert.assertEquals(expect, actual)
    }
}