package ru.nehodov.sqotlin

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.extensions.LESSorEQ

class LessOrEqStringExtTest {

    val column_a = "column_a"
    val column_b = "column_b"

    @Test
    fun `LESSorEQ when b is String then a = b`() {
        val expect = """
            |$column_a <= $column_b
        """.trimMargin("|")

        val actual = column_a LESSorEQ column_b

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `LESS when b is Int then a = intValue`() {
        val intValue = 5
        val expect = """
            |$column_a <= $intValue
        """.trimMargin("|")

        val actual = column_a LESSorEQ intValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `LESSorEQ when b is Byte then a = byteValue`() {
        val byteValue: Byte = 1
        val expect = """
            |$column_a <= $byteValue
        """.trimMargin("|")

        val actual = column_a LESSorEQ byteValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `LESSorEQ when b is Long then a = longValue`() {
        val longValue = 1L
        val expect = """
            |$column_a <= $longValue
        """.trimMargin("|")

        val actual = column_a LESSorEQ longValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `LESSorEQ when b is Float then a = floatValue`() {
        val floatValue = 1f
        val expect = """
            |$column_a <= $floatValue
        """.trimMargin("|")

        val actual = column_a LESSorEQ floatValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `LESSorEQ when b is Double then a = doubleValue`() {
        val doubleValue = 1.0
        val expect = """
            |$column_a <= $doubleValue
        """.trimMargin("|")

        val actual = column_a LESSorEQ doubleValue

        Assert.assertEquals(expect, actual)
    }
}