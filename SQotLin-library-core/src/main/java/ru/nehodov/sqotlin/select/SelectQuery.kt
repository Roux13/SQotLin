package ru.nehodov.sqotlin.select

class SelectQuery(private val isDistinct: Boolean) {

    private var columns: String? = ""
    private var from: String? = null

    //    var from: SELECT.From? = null
    private val _joins: MutableList<String?> = mutableListOf()
    private val joins: String?
        get() = if (_joins.isNotEmpty()) _joins.joinToString(
            prefix = "",
            separator = "\n"
        ) else null
    private var where: String? = null
    private var orderBy: String? = null
    private var limit: String? = null
    private var groupBy: String? = null
    private var having: String? = null

    fun sql(): String {
        val query = StringBuilder(
            """
            |$SELECT${if (isDistinct) " $DISTINCT" else ""}
            |   ${columns ?: ""}
            """
        )
        from?.let { query.append("|$from") }
        joins?.let { query.appendLine().append("|$joins") }
        where?.let { query.appendLine().append("|$where") }
        groupBy?.let { query.appendLine().append("|$groupBy") }
        having?.let { query.appendLine().append("|$having")}
        orderBy?.let { query.appendLine().append("|$orderBy")}
        limit?.let { query.appendLine().append("|$limit")}
        return query.toString().trimMargin().trim()
    }

    fun setColumns(vararg columns: String) {
        val sb = StringBuilder()
        columns.forEach {
            sb.append("   $it,").appendLine()
        }
        this.columns = sb.toString().trim().trimEnd(',')
    }

    fun setTables(vararg tables: String) {
        val sb = StringBuilder("""|$FROM""")
        tables.forEach {
            sb.appendLine().append("|   $it,")
        }
        from = sb.toString().trimEnd(',').trim().trimMargin()
    }

    fun addJoin(join: String) {
        _joins.add(join)
    }

    fun addWhere(rowFilter: String) {
        where = """
            |$WHERE
            |   $rowFilter
        """.trimMargin()
    }

    fun addGroupBy(expr: String) {
        groupBy = """
            |$GROUP_BY
            |   $expr
            """.trimMargin()
    }

    fun addHaving(expr: String) {
        having = "$HAVING $expr"
    }

    fun addOrderBy(orderingTerm: String) {
        orderBy = "$ORDER_BY $orderingTerm"
    }

    fun addLimit(expr: String) {
        limit = "$LIMIT $expr"
    }
//    private fun embedColumns(): String {
//        val sb = StringBuilder()
//        columns.forEach {
//            sb.append("   $it,").appendLine()
//        }
//        return sb.toString().trim().trimEnd(',')
//    }
//
//    fun embedTables(): String {
//        val sb = StringBuilder("""|FROM""")
//        tables.forEach {
//            sb.appendLine().append("|   $it,")
//        }
//        return sb.toString().trimEnd(',')
//    }

    companion object {
        const val SELECT = "SELECT"
        const val DISTINCT = "DISTINCT"
        const val FROM = "FROM"
        const val WHERE = "WHERE"
        const val GROUP_BY = "GROUP BY"
        const val HAVING = "HAVING"
        const val ORDER_BY = "ORDER BY"
        const val LIMIT = "LIMIT"
        const val SPACES = "   "
    }
}
