package ru.nehodov.sqotlin.extensions

import org.junit.Assert
import org.junit.Test

class InStringExtTest {

    val value = "value"

    @Test
    fun `IN when one value and value is string`() {
        val stringValue = "one"
        val expect = """
            |$value IN ($stringValue)
        """.trimMargin("|")

        val actual = value.IN(stringValue)

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `IN when two values and values are String`() {
        val stringValue1 = "one"
        val stringValue2 = "two"
        val expect = """
            |$value IN ($stringValue1, $stringValue2)
        """.trimMargin("|")

        val actual = value.IN(stringValue1, stringValue2)

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `IN when one value and value is Int`() {
        val intValue = 1
        val expect = """
            |$value IN ($intValue)
        """.trimMargin("|")

        val actual = value.IN(intValue)

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `IN when two values and values are Int`() {
        val intValue1 = 1
        val intValue2 = 2
        val expect = """
            |$value IN ($intValue1, $intValue2)
        """.trimMargin("|")

        val actual = value.IN(intValue1, intValue2)

        Assert.assertEquals(expect, actual)
    }
}