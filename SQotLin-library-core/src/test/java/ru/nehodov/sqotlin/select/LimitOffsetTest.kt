package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.TestDbSchemaConst

class LimitOffsetTest {

    @Test
    fun `when simple LIMIT`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   ${TestDbSchemaConst.first_table}
            |LIMIT 1
        """.trimMargin()

        val actual =
            SELECT(
                ALL
            ).FROM(
                TestDbSchemaConst.first_table
            ).LIMIT(1).query()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when simple LIMIT with OFFSET`() {
        val expect = """
            |SELECT
            |   *
            |FROM
            |   ${TestDbSchemaConst.first_table}
            |LIMIT 1 OFFSET 10
        """.trimMargin()

        val actual =
            SELECT(
                ALL
            ).FROM(
                TestDbSchemaConst.first_table
            ).LIMIT(1).OFFSET(10).query()

        Assert.assertEquals(expect, actual)
    }
}