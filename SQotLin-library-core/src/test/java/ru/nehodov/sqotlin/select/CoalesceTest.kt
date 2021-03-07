package ru.nehodov.sqotlin.select

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.EMPTY
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.TestDbSchemaConst.column_d
import ru.nehodov.sqotlin.TestDbSchemaConst.column_e
import ru.nehodov.sqotlin.aggregateFunctions.SUM
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS

class CoalesceTest {

    companion object {
        private const val DEFAULT_VARCHAR = "''"
        private const val DEFAULT_NUMBER = 0
    }

    @Test
    fun `when 1 column & default varchar`() {
        val expect = """
            |COALESCE($column_a, $EMPTY)
        """.trimMargin()

        val actual = COALESCE(column_a, default = EMPTY).AS_IS()

        assertEquals(expect, actual)
    }

    @Test
    fun `when 1 column & default number`() {
        val expect = """
            |COALESCE($column_a, $DEFAULT_NUMBER)
        """.trimMargin()

        val actual = COALESCE(column_a, default = DEFAULT_NUMBER).AS_IS()

        assertEquals(expect, actual)
    }

    @Test
    fun `when 1 column & default varchar & alias`() {
        val expect = """
            |COALESCE($column_a, $EMPTY) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(column_a, default = EMPTY) AS alias_a

        assertEquals(expect, actual)
    }

    @Test
    fun `when 1 column & default number $ alias`() {
        val expect = """
            |COALESCE($column_a, $DEFAULT_NUMBER) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(column_a, default = DEFAULT_NUMBER) AS alias_a

        assertEquals(expect, actual)
    }

    @Test
    fun `when 2 columns & default varchar`() {
        val expect = """
            |COALESCE($column_a, $column_b, $EMPTY)
        """.trimMargin()

        val actual = COALESCE(column_a, column_b, default = EMPTY).AS_IS()

        assertEquals(expect, actual)
    }

    @Test
    fun `when 2 columns & default number`() {
        val expect = """
            |COALESCE($column_a, $column_b, $DEFAULT_NUMBER)
        """.trimMargin()

        val actual = COALESCE(column_a, column_b, default = DEFAULT_NUMBER).AS_IS()

        assertEquals(expect, actual)
    }

    @Test
    fun `when 2 columns & default varchar & alias`() {
        val expect = """
            |COALESCE($column_a, $column_b, $EMPTY) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(column_a, column_b, default = EMPTY) AS alias_a

        assertEquals(expect, actual)
    }

    @Test
    fun `when 2 columns & default number $ alias`() {
        val expect = """
            |COALESCE($column_a, $column_b, $DEFAULT_NUMBER) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(column_a, column_b, default = DEFAULT_NUMBER) AS alias_a

        assertEquals(expect, actual)
    }

    @Test
    fun `when 5 columns & default number $ alias`() {
        val expect = """
            |COALESCE($column_a, $column_b, $column_c, $column_d, $column_e, $DEFAULT_NUMBER) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(column_a, column_b, column_c, column_d, column_e, default = DEFAULT_NUMBER) AS alias_a

        assertEquals(expect, actual)
    }

    @Test
    fun `when 2 columns & of them is SUM & default number $ alias`() {
        val expect = """
            |COALESCE(SUM($column_a), $column_b, $DEFAULT_NUMBER) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(SUM(column_a), column_b, default = DEFAULT_NUMBER) AS alias_a

        assertEquals(expect, actual)
    }
}