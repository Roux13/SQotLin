package ru.nehodov.sqotlin.insert

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.first_table

class InsertTest {

    @Test
    fun `when INSERT 1 column & value`() {
        val expect = """
            |INSERT INTO $first_table (
            |       $column_a
            |)
            |VALUES (
            |       0
            |)
        """.trimMargin("|")

        val actual =
            INSERT_INTO(first_table)
                .COLUMNS(
                    column_a
                ).VALUES(
                    0
                )

        assertEquals(expect, actual)
    }

    @Test
    fun `when INSERT 2 columns & values`() {
        val expect = """
            |INSERT INTO $first_table (
            |       $column_a,
            |       $column_b
            |)
            |VALUES (
            |       0,
            |       'varchar'
            |)
        """.trimMargin("|")

        val actual =
            INSERT_INTO(first_table)
                .COLUMNS(
                    column_a,
                    column_b,
                ).VALUES(
                    0,
                    "'varchar'"
                )

        assertEquals(expect, actual)
    }
}