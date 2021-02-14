package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.EMPTY
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS

class IFNULL_Test {
    private val column_a = "column_a"
    private val alias_a = "alias_a"

    @Test
    fun `when IFNULL as is & default is empty varchar`(){
        val expect = """
            |IFNULL($column_a, '')
        """.trimMargin("|")

        val actual = IFNULL(column_a, EMPTY).AS_IS()


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with alias & default is empty varchar`(){
        val expect = """
            |IFNULL($column_a, '') AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, EMPTY).AS("alias_a")


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with const alias & default is empty varchar`(){
        val expect = """
            |IFNULL($column_a, '') AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, EMPTY).AS(alias_a)


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL as is & default is integer`(){
        val expect = """
            |IFNULL($column_a, 0)
        """.trimMargin("|")

        val actual = IFNULL(column_a, 0).AS_IS()


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with alias & default is integer`(){
        val expect = """
            |IFNULL($column_a, 0) AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, 0).AS("alias_a")


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with const alias & default is integer`(){
        val expect = """
            |IFNULL($column_a, 0) AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, 0).AS(alias_a)


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL as is & default is real`(){
        val expect = """
            |IFNULL($column_a, 1.0)
        """.trimMargin("|")

        val actual = IFNULL(column_a, 1.0).AS_IS()


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with alias & default is real`(){
        val expect = """
            |IFNULL($column_a, 1.0) AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, 1.0).AS("alias_a")


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with const alias & default is real`(){
        val expect = """
            |IFNULL($column_a, 1.0) AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, 1.0).AS(alias_a)


        Assert.assertEquals(expect, actual)
    }
}