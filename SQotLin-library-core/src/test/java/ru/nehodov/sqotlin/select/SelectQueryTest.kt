package ru.nehodov.sqotlin.select

import org.junit.Assert.assertEquals
import org.junit.Test

class SelectQueryTest {

    private val column_a = "column_a"
    private val column_b = "column_b"


//    @Test
//    fun `when embedColumnList`() {
//        val query = SelectQuery().apply {
//            columns.addAll(listOf(column_a, column_b))
//        }
//        val expect = """
//            |   $column_a,
//            |   $column_b
//        """.trimMargin()
//
//        val actual = query.embedColumnList()
//
//        assertEquals(expect, actual)
//    }

    @Test
    fun `when sql`() {
        val query = SelectQuery(false)
        query.setColumns(column_a, column_b)

        val expect = """
            |SELECT
            |   $column_a,
            |   $column_b
        """.trimMargin()

        val actual = query.sql()

        assertEquals(expect, actual)
    }
}