package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a

class LikeTest {

    @Test
    fun `when just LIKE`() {
        val pattern = "%pattern"
        val expect = """
            |$column_a LIKE '${pattern}'
        """.trimMargin()

        val actual = column_a LIKE pattern

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when just LIKE & pattern surrounded in single quotes `() {
        val expect = """
            |$column_a LIKE '%pattern'
        """.trimMargin()

        val actual = column_a LIKE "'%pattern'"

        Assert.assertEquals(expect, actual)
    }
}