package ru.nehodov.sqotlin

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.core.GREATorEQ

class GreatOrEqStringExtTest {

    val column_a = "column_a"
    val column_b = "column_b"

    @Test
    fun `GREATorEQ when b is String then a = b`() {
        val expect = """
            |$column_a >= $column_b
        """.trimMargin("|")

        val actual = column_a GREATorEQ column_b

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `GREATorEQ when b is Int then a = intValue`() {
        val intValue = 5
        val expect = """
            |$column_a >= $intValue
        """.trimMargin("|")

        val actual = column_a GREATorEQ intValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `GREATorEQ when b is Byte then a = byteValue`() {
        val byteValue: Byte = 1
        val expect = """
            |$column_a >= $byteValue
        """.trimMargin("|")

        val actual = column_a GREATorEQ byteValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `GREATorEQ when b is Long then a = longValue`() {
        val longValue = 1L
        val expect = """
            |$column_a >= $longValue
        """.trimMargin("|")

        val actual = column_a GREATorEQ longValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `GREATorEQ when b is Float then a = floatValue`() {
        val floatValue = 1f
        val expect = """
            |$column_a >= $floatValue
        """.trimMargin("|")

        val actual = column_a GREATorEQ floatValue

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `GREATorEQ when b is Double then a = doubleValue`() {
        val doubleValue = 1.0
        val expect = """
            |$column_a >= $doubleValue
        """.trimMargin("|")

        val actual = column_a GREATorEQ doubleValue

        Assert.assertEquals(expect, actual)
    }
}