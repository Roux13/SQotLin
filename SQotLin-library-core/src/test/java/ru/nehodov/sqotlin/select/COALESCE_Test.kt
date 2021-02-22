package ru.nehodov.sqotlin.select

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.aggregateFunctions.SUM
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS

class COALESCE_Test {

    companion object {
        private val column_a = "column_a"
        private val column_b = "column_b"
        private val column_c = "column_c"
        private val column_d = "column_d"
        private val column_e = "column_e"
        private val alias_a = "alias_a"
        private const val DEFAULT_VARCHAR = "''"
        private const val DEFAULT_NUMBER = 0
    }

    @Test
    fun `when 1 column & default varchar`() {
        val expect = """
            |COALESCE($column_a, $DEFAULT_VARCHAR)
        """.trimMargin()

        val actual = COALESCE(column_a, default = DEFAULT_VARCHAR).AS_IS()

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
            |COALESCE($column_a, $DEFAULT_VARCHAR) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(column_a, default = DEFAULT_VARCHAR) AS alias_a

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
            |COALESCE($column_a, $column_b, $DEFAULT_VARCHAR)
        """.trimMargin()

        val actual = COALESCE(column_a, column_b, default = DEFAULT_VARCHAR).AS_IS()

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
            |COALESCE($column_a, $column_b, $DEFAULT_VARCHAR) AS $alias_a
        """.trimMargin()

        val actual = COALESCE(column_a, column_b, default = DEFAULT_VARCHAR) AS alias_a

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