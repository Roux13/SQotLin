package ru.nehodov.sqotlin

//@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [28])
class Test {

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
//    private val table_name = "table_name"
//    private val column_a = "column_a"
//    private val column_b = "column_b"
//    private val table_alias = "table_alias"
//    private val alias_a = "alias_a"
//    private val alias_b = "alias_b"
//
//    @Test
//    fun `when`() {
//
//
//        db.execSQL(
//            SELECT(
//                SUM(column_a) AS "sum",
//                IFNULL(column_b, "''") AS alias_a,
//                COALESCE(column_a, column_b, 0.toString()) AS alias_b,
//                "1" AS alias_a,
//            ).FROM(
//                table_name AS "a",
//                table_name,
//                SELECT(SQLiteConst.ALL).FROM(table_name).sql()
//            ).WHERE(column_a EQ 5)
//                .sql()
//        )
//
//        val SELECT_ALL_FROM = SELECT(SQLiteConst.ALL).FROM(table_name).sql()
//
//        SELECT(
//            SUM(column_a) AS "sum",
//            column_b
//        ).FROM(
//            column_a AS "a",
//            column_b,
//            SELECT_ALL_FROM AS "alis3"
//        ).INNER_JOIN(
//            table_name ON column_a EQ column_b
//        ).INNER_JOIN(
//            table_name ON column_b NEQ column_a
//        ).LEFT_JOIN(
//            table_name ON (column_a EQ column_b)
//        ).CROSS_JOIN(
//            SELECT(SQLiteConst.ALL).FROM(table_name).sql() AS alias_b
//        ).WHERE(table_name EQ 1)
//            .sql()
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
//        ).INNER_JOIN(
//            table_name ON column_a EQ column_b
//        ).INNER_JOIN(
//            table_name ON column_b NEQ column_a
//        ).LEFT_JOIN(
//            table_name ON (column_a EQ column_b)
//        ).CROSS_JOIN(
//            SELECT(SQLiteConst.ALL).FROM(table_name).sql() AS alias_b
//        ).WHERE(table_name EQ 1)
//            .sql()
//    }
}