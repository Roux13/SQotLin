package ru.nehodov.sqotlin

//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import androidx.test.core.app.ApplicationProvider
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//import ru.nehodov.sqotlin.aggregateFunctions.SUM
//import ru.nehodov.sqotlin.extensions.*
//import ru.nehodov.sqotlin.select.COALESCE
//import ru.nehodov.sqotlin.select.IFNULL
//import ru.nehodov.sqotlin.select.SELECT
//
//@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [28])
//class Test {
//
//    private val table_name = "table_name"
//    private val column_a = "column_a"
//    private val column_b = "column_b"
//    private val table_alias = "table_alias"
//    private val alias_a = "alias_a"
//    private val alias_b = "alias_b"
//
//    private lateinit var helper: TestOpenHelper
//    private lateinit var db: SQLiteDatabase
//
//    @Before
//    fun setUp() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        helper = TestOpenHelper(context, "path", null, 1)
//        db = helper.writableDatabase
//    }
//
//    @After
//    fun shutDown() {
//        helper.close()
//    }
//
//    @Test
//    fun `when`() {
//        db.execSQL(
//            SELECT(SQLiteConst.ALL).FROM(table_name).query()
//        )
//
//        db.execSQL(
//            SELECT(
//                SUM(column_a) AS "sum",
//                IFNULL(column_b, "''") AS alias_a,
//                COALESCE(column_a, column_b, default = 0) AS alias_b,
//                "1" AS alias_a
//            ).FROM(
//                SELECT(SQLiteConst.ALL).FROM(table_name).query()
//            ).WHERE(column_a EQ 5)
//                .query()
//        )
//
//        val SELECT_ALL_FROM = SELECT(SQLiteConst.ALL).FROM(table_name).query()
//
//        SELECT(
//            SUM(column_a) AS "sum",
//            column_b
//        ).FROM(
//            column_a AS "a",
//            column_b,
//            SELECT_ALL_FROM AS "alis3"
//        ).INNER_JOIN(
//            table_name
//        ).ON(
//            column_a EQ column_b
//        ).INNER_JOIN(table_name).ON(
//            column_b NEQ column_a
//        ).LEFT_JOIN(table_name).ON(
//            column_a EQ column_b
//        ).CROSS_JOIN(
//            SELECT(SQLiteConst.ALL).FROM(table_name).query() AS alias_b
//        ).ON(
//            column_a EQ column_b
//        ).WHERE(table_name EQ 1)
//            .query()
//
//
//        SELECT(
//            SUM(column_a) AS "sum",
//            column_b
//        ).FROM(
//            column_a AS "a",
//            column_b,
//            SELECT(SQLiteConst.ALL).FROM(table_name)
//                    UNION_ALL
//                    SELECT(column_a).FROM(table_name)
//        ).INNER_JOIN(table_name).ON(
//            column_a EQ column_b
//        ).INNER_JOIN(table_name).ON(
//            column_b NEQ column_a
//        ).LEFT_JOIN(table_name).ON(
//            column_a EQ column_b
//        ).CROSS_JOIN(
//            SELECT(SQLiteConst.ALL).FROM(table_name) AS alias_b
//        ).ON(
//            column_a NEQ column_b
//        ).WHERE(table_name EQ 1
//        ).query()
//    }
//}