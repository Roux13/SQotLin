package ru.nehodov.sqotlin.common

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.column_d
import ru.nehodov.sqotlin.TestDbSchemaConst.column_e
import ru.nehodov.sqotlin.aggregateFunctions.COUNT
import ru.nehodov.sqotlin.extensions.GREATER

class CaseTest {

    @Test
    fun `when case without case expression and one when`() {
        val expect = """
            |CASE
            |     WHEN $column_a > 0 THEN $column_b
            |END
        """.trimMargin()

        val actual =
            CASE().WHEN(column_a GREATER 0, column_b)
                .END()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when case without case expression and 2 whens`() {
        val expect = """
            |CASE
            |     WHEN $column_a > 0 THEN $column_b
            |     WHEN $column_c > 0 THEN $column_d
            |END
        """.trimMargin()

        val actual =
            CASE()
                .WHEN(column_a GREATER 0, column_b)
                .WHEN(column_c GREATER 0, column_d)
                .END()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when case without case expression & 1 when & 1 else`() {
        val expect = """
            |CASE
            |     WHEN $column_a > 0 THEN $column_b
            |     ELSE $column_d
            |END
        """.trimMargin()

        val actual =
            CASE()
                .WHEN(column_a GREATER 0, column_b)
                .ELSE(column_d)
                .END()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when case without case expression & 2 whens & 1 else`() {
        val expect = """
            |CASE
            |     WHEN $column_a > 0 THEN $column_b
            |     WHEN $column_c > 0 THEN $column_d
            |     ELSE $column_e
            |END
        """.trimMargin()

        val actual =
            CASE()
                .WHEN(column_a GREATER 0, column_b)
                .WHEN(column_c GREATER 0, column_d)
                .ELSE(column_e)
                .END()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when case & one when`() {
        val expect = """
            |CASE COUNT($column_a)
            |     WHEN 0 THEN $column_b
            |END
        """.trimMargin()

        val actual =
            CASE(COUNT(column_a))
                .WHEN(0, column_b)
                .END()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when case & 2 whens`() {
        val expect = """
            |CASE COUNT($column_a)
            |     WHEN 0 THEN $column_b
            |     WHEN 1 THEN $column_d
            |END
        """.trimMargin()

        val actual =
            CASE(COUNT(column_a))
                .WHEN(0, column_b)
                .WHEN(1, column_d)
                .END()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when case & 1 when & 1 else`() {
        val expect = """
            |CASE COUNT($column_a)
            |     WHEN 0 THEN $column_b
            |     ELSE $column_d
            |END
        """.trimMargin()

        val actual =
            CASE(COUNT(column_a))
                .WHEN(0, column_b)
                .ELSE(column_d)
                .END()

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when case & 2 whens & 1 else`() {
        val expect = """
            |CASE COUNT($column_a)
            |     WHEN 0 THEN $column_b
            |     WHEN 1 THEN $column_d
            |     ELSE $column_e
            |END
        """.trimMargin()

        val actual =
            CASE(COUNT(column_a))
                .WHEN(0, column_b)
                .WHEN(1, column_d)
                .ELSE(column_e)
                .END()

        Assert.assertEquals(expect, actual)
    }
}