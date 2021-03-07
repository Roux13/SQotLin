package ru.nehodov.sqotlin.select

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b
import ru.nehodov.sqotlin.TestDbSchemaConst.column_c
import ru.nehodov.sqotlin.aggregateFunctions.AVG
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS

class SelectTest {

    @Test
    fun `when SELECT expression`() {
        val expect = """
            |SELECT
            |   a + a
        """.trimMargin("|")

        val actual =
                SELECT(
                        "a + a"
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT DISTINCT expression`() {
        val expect = """
            |SELECT DISTINCT
            |   a + a
        """.trimMargin("|")

        val actual =
                SELECT_DISTINCT(
                        "a + a"
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT any string AS alias`() {
        val expect = """
            |SELECT
            |   0 AS $alias_a
        """.trimMargin("|")

        val actual =
                SELECT(
                        "0" AS alias_a
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT 1 column`() {
        val expect = """
            |SELECT
            |   $column_a
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a
            ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT 2 columns`() {
        val expect = """
            |SELECT
            |   $column_a,
            |   $column_b
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a,
                column_b
            ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT 2 columns & one of them with alias`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a AS alias_a,
                column_b
            ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT 2 columns & one of them by condition & condition true`() {
        val condition = true
        val expect = """
            |SELECT
            |   $column_a,
            |   $column_b
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a,
                if(condition) column_b else ""
            ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT 2 columns & one of them by condition & condition false`() {
        val condition = false
        val expect = """
            |SELECT
            |   $column_a
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a,
                if(condition) column_b else ""
            ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT 3 columns & one of them by condition & condition false`() {
        val condition = false
        val expect = """
            |SELECT
            |   $column_a,
            |   $column_c
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a,
                if(condition) column_b else "",
                column_c
            ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT 2 columns & all with aliases`() {
        val expect = """
            |SELECT
            |   $column_a AS $alias_a,
            |   $column_b AS $alias_b
        """.trimMargin("|")

        val actual =
            SELECT(
                column_a AS alias_a,
                column_b AS alias_b,
            ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with AVG without alias`() {
        val expect = """
            |SELECT
            |   AVG($column_a)
        """.trimMargin()

        val actual =
                SELECT(
                    AVG(column_a).AS_IS()
                ).query()

        assertEquals(expect, actual)
    }

    @Test
    fun `when SELECT with AVG with alias`() {
        val expect = """
            |SELECT
            |   AVG($column_a) AS $alias_a
        """.trimMargin()

        val actual =
                SELECT(
                        AVG(column_a) AS alias_a
                ).query()

        assertEquals(expect, actual)
    }


}