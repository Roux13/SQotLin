package ru.nehodov.sqotlin.select

import org.junit.Assert
import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.EMPTY
import ru.nehodov.sqotlin.TestDbSchemaConst.alias_a
import ru.nehodov.sqotlin.TestDbSchemaConst.column_a
import ru.nehodov.sqotlin.aggregateFunctions.SUM
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.AS_IS
import ru.nehodov.sqotlin.extensions.EQ

class IfNullTest {

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

        val actual = IFNULL(column_a, EMPTY) AS "alias_a"


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with const alias & default is empty varchar`(){
        val expect = """
            |IFNULL($column_a, '') AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, EMPTY) AS alias_a


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

        val actual = IFNULL(column_a, 0) AS "alias_a"


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with const alias & default is integer`(){
        val expect = """
            |IFNULL($column_a, 0) AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, 0) AS alias_a


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

        val actual = IFNULL(column_a, 1.0) AS "alias_a"


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with const alias & default is real`(){
        val expect = """
            |IFNULL($column_a, 1.0) AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(column_a, 1.0) AS alias_a


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with sum, const, alias & default is real`(){
        val expect = """
            |IFNULL(SUM($column_a), 1.0) AS alias_a
        """.trimMargin("|")

        val actual = IFNULL(SUM(column_a), 1.0) AS alias_a


        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with sum, const, alias & = 1,0`(){
        val expect = """
            |IFNULL(SUM($column_a), 1.0) AS alias_a = 1.0
        """.trimMargin("|")

        val actual = IFNULL(SUM(column_a), 1.0) AS alias_a EQ 1.0

        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `when IFNULL with sum, const, alias & = second`(){
        val expect = """
            |IFNULL(SUM($column_a), '') AS alias_a = second
        """.trimMargin("|")

        val actual = IFNULL(SUM(column_a), EMPTY) AS alias_a EQ "second"

        Assert.assertEquals(expect, actual)
    }
}