package ru.nehodov.sqotlin

import org.junit.Test
import ru.nehodov.sqotlin.SQLiteConst.ALL
import ru.nehodov.sqotlin.aggregateFunctions.SUM
import ru.nehodov.sqotlin.extensions.AS
import ru.nehodov.sqotlin.extensions.UNION_ALL
import ru.nehodov.sqotlin.select.COALESCE
import ru.nehodov.sqotlin.select.IFNULL
import ru.nehodov.sqotlin.select.SELECT

class DemoTest {

    private val table_name = "table_name"
    private val column_a = "column_a"
    private val column_b = "column_b"
    private val table_alias = "table_alias"
    private val alias_a = "alias_a"
    private val alias_b = "alias_b"

    @Test
    fun `when`() {
        SELECT(
            SUM(column_a) AS "sum",
            IFNULL(column_b, "") AS alias_a,
            COALESCE(column_a, column_b, "") AS alias_b,
            "1" AS alias_a,
        ).FROM(
            column_a AS "a",
            column_b,
            SELECT(ALL).FROM(table_name).sql()
        ).WHERE(column_a EQ 5)
            .sql()

        val SELECT_ALL_FROM = SELECT(ALL).FROM(table_name).sql()

        SELECT(
            SUM(column_a) AS "sum",
            column_b
        ).FROM(
            column_a AS "a",
            column_b,
            SELECT_ALL_FROM AS "alis3"
        ).INNER_JOIN(
            table_name ON column_a EQ column_b
        ).INNER_JOIN(
            table_name ON column_b NEQ column_a
        ).LEFT_JOIN(
            table_name ON (column_a EQ column_b)
        ).CROSS_JOIN(
            SELECT(ALL).FROM(table_name).sql() AS alias_b
        ).WHERE(table_name EQ 1)
            .sql()


        SELECT(
            SUM(column_a) AS "sum",
            column_b
        ).FROM(
            column_a AS "a",
            column_b,
            SELECT(ALL).FROM(table_name)
                    UNION_ALL
            SELECT(column_a).FROM(table_name)
        ).INNER_JOIN(
            table_name ON column_a EQ column_b
        ).INNER_JOIN(
            table_name ON column_b NEQ column_a
        ).LEFT_JOIN(
            table_name ON (column_a EQ column_b)
        ).CROSS_JOIN(
            SELECT(ALL).FROM(table_name).sql() AS alias_b
        ).WHERE(table_name EQ 1)
            .sql()
    }

}


//val testQuery = """
//|SELECT
//|     IFNULL(arts.name, '') as $NAME_ART,
//|     IFNULL(arts.price, 0) as $PRICE_ART,
//|     IFNULL(arts.priceDiscount, 0) as $PRICE_ART_DISCOUNT,
//|     IFNULL(arts.Attr1, 0) as $ATTR1_ART,
//|     IFNULL(arts.Attr2, 0) as $ATTR2_ART,
//|     IFNULL(arts.Attr3, 0) as $ATTR3_ART,
//|     IFNULL(arts.Attr4, 0) as $ATTR4_ART,
//|     IFNULL(arts.Attr5, 0) as $ATTR5_ART,
//|     IFNULL(arts.Attr6, 0) as $ATTR6_ART,
//|     IFNULL(arts.Attr7, 0) as $ATTR7_ART,
//|     IFNULL(arts.Attr8, 0) as $ATTR8_ART,
//|     IFNULL(arts.Attr9, 0) as $ATTR9_ART,
//|     IFNULL(arts.Attr10, 0) as $ATTR10_ART,
//|     IFNULL(arts.MarkType, 0) as $MARK_TYPE,
//|     IFNULL(arts.UseSN, 0) as $USE_SN,
//|     1 as $COEF,
//|     '' as $BARC_NAME,
//|     IFNULL(arts.id, '') as $ART_ID_N,
//|     IFNULL(dst.limitquant, 0) as $DST_TASK,
//|     COALESCE(dst.limitquant, arts.Rest, 0) as $DST_LIMIT,
//|     IFNULL(dst.cell, 0) as $DST_CELL,
//|     IFNULL(dst.SN, '') as $DST_SN,
//|     0 as $TASK_PRICE
//|FROM (SELECT artID,
//|             barcode,
//|             sn,
//|             docID,
//|             cell,
//|             SUM(Qty) AS limitquant
//|     FROM ${DocSelectLogConst.TABLE}
//|     WHERE  docID IN $docOutIDQuery AND BarcodePDF = ${kiz.toSql()}
//|     GROUP BY artID, barcode, sn
//|     ) as dst
//|INNER JOIN (SELECT *  FROM ${DbArtConst.TABLE}) as arts ON arts.id = dst.artID
//""".trimMargin("|")