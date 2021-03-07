package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table
import ru.nehodov.sqotlin.extensions.ASC
import ru.nehodov.sqotlin.extensions.DESC

class OrderByTest {

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

        Assert.assertEquals(expext, actual)
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

        Assert.assertEquals(expext, actual)
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

        Assert.assertEquals(expext, actual)
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

        Assert.assertEquals(expext, actual)
    }
}