package ru.nehodov.sqotlin.select

class SelectQuery(private val isDistinct: Boolean) {

    private var columns: String? = ""
    private var from: String? = null
    private val _joins: MutableList<String?> = mutableListOf()
    private val joins: String?
        get() = if (_joins.isNotEmpty()) _joins.joinToString(
            prefix = "",
            separator = "\n"
        ) else null
    private var where: String? = null
    private var orderBy: String? = null
    private var groupBy: String? = null
    private var having: String? = null
    private var limit: String? = null
    private var offset: String? = null

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
        having?.let { query.appendLine().append("|$having") }
        orderBy?.let { query.appendLine().append("|$orderBy") }
        limit?.let { query.appendLine().append("|$limit") }
        offset?.let { query.append(" $offset") }
        return query.toString().trimMargin().trim()
    }

    fun subQuery(): String {
        val query = StringBuilder(
            """
            |($SELECT${if (isDistinct) " $DISTINCT" else ""}
            |   ${columns ?: ""}
            """
        )
        from?.let { query.append("|$from") }
        joins?.let { query.appendLine().append("|$joins") }
        where?.let { query.appendLine().append("|$where") }
        groupBy?.let { query.appendLine().append("|$groupBy") }
        having?.let { query.appendLine().append("|$having") }
        orderBy?.let { query.appendLine().append("|$orderBy") }
        limit?.let { query.appendLine().append("|$limit") }
        offset?.let { query.append(" $offset") }
        query.appendLine().append("|)")
        return query.toString().trimMargin().trim()
    }

    fun setColumns(vararg columns: String) {
        this.columns = columns.joinToString(",\n   ")
    }

    fun setTables(vararg tables: String) {
        val _tables = """
            |${tables.joinToString(",\n\t")}
        """.trimMargin()
//        val fromBuilder = StringBuilder(
//            """
//            |$FROM
//            |   $_tables
//        """
//        )
        from = """
            |$FROM
            |   $_tables
        """.trimMargin()
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

    fun addGroupBy(vararg expr: String) {
        groupBy = """
            |$GROUP_BY
            |   ${expr.joinToString(",\n   ")}
            """.trimMargin()
    }

    fun addHaving(expr: String) {
        having = """
            |$HAVING
            |   $expr
        """.trimMargin()
    }

    fun addOrderBy(vararg orderingTerms: String) {
        orderBy = """
            |$ORDER_BY 
            |   ${orderingTerms.joinToString(",\n   ")}
        """.trimMargin()
    }

    fun addLimit(limit: Int) {
        this.limit = "$LIMIT $limit"
    }

    fun addOffset(offset: Int) {
        this.offset = "$OFFSET $offset"
    }

    companion object {
        const val SELECT = "SELECT"
        const val DISTINCT = "DISTINCT"
        const val FROM = "FROM"
        const val WHERE = "WHERE"
        const val GROUP_BY = "GROUP BY"
        const val HAVING = "HAVING"
        const val ORDER_BY = "ORDER BY"
        const val LIMIT = "LIMIT"
        const val OFFSET = "OFFSET"
        const val SPACES = "   "
    }
}
