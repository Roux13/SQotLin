package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test

class EqStringExtTest {

    val column_a = "column_a"
    val column_b = "column_b"

    @Test
    fun `EQ when b is String then a = b`() {
        val expect = """
            |$column_a = $column_b
        """.trimMargin("|")

        val actual = column_a EQ column_b

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `EQ when b is Int then a = intValue`() {
        val intValue = 5
        val expect = """
            |$column_a = $intValue
        """.trimMargin("|")

        val actual = column_a EQ intValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `EQ when b is Byte then a = byteValue`() {
        val byteValue: Byte = 1
        val expect = """
            |$column_a = $byteValue
        """.trimMargin("|")

        val actual = column_a EQ byteValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `EQ when b is Long then a = longValue`() {
        val longValue = 1L
        val expect = """
            |$column_a = $longValue
        """.trimMargin("|")

        val actual = column_a EQ longValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `EQ when b is Float then a = floatValue`() {
        val floatValue = 1f
        val expect = """
            |$column_a = $floatValue
        """.trimMargin("|")

        val actual = column_a EQ floatValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `EQ when b is Double then a = doubleValue`() {
        val doubleValue = 1.0
        val expect = """
            |$column_a = $doubleValue
        """.trimMargin("|")

        val actual = column_a EQ doubleValue

        Assert.assertEquals(expect, actual)
    }
}