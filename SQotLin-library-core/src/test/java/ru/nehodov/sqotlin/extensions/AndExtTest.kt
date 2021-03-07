package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_b

class AndExtTest {

    @Test
    fun `when simple and`() {
        val expect = """
            |$column_a = 0
            |   AND $column_b > 0
        """.trimMargin()

        val actual = column_a EQ 0 AND column_b GREAT 0

        Assert.assertEquals(expect, actual)
    }


}