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
        return StringBuilder(
            """
            |$SELECT${if (isDistinct) " $DISTINCT" else ""}
            """
        ).append(build()).toString().trimMargin().trim()
    }

    fun subQuery(): String {
        val closingParenthesis = """
            |)"""
        return StringBuilder(
            """(
            |$SELECT${if (isDistinct) " $DISTINCT" else ""}
            """
        ).append(build())
            .append(closingParenthesis).toString()
    }

    private fun build(): StringBuilder {
        val query = StringBuilder()
        columns?.let { query.append("$columns") }
        from?.let { query.append("$from") }
        joins?.let { query.append("$joins") }
        where?.let { query.append("$where") }
        groupBy?.let { query.append("$groupBy") }
        having?.let { query.append("$having") }
        orderBy?.let { query.append("$orderBy") }
        limit?.let { query.append("$limit") }
        offset?.let { query.append(" $offset") }
        return query
    }

    fun setColumns(vararg columns: String) {
        val _columns = StringBuilder()
        columns.forEach { column ->
            val _column = StringBuilder()
            column.trimMargin().split("\n").forEach{ line ->
                _column.append("|   $line\n")
            }
            _columns.append(_column.trimEnd()).append(",\n")
        }
        this.columns = _columns.toString().trimEnd().trimEnd(',').trimEnd()
//        this.columns = columns.joinToString(prefix = "", separator = ",\n") { "|   ${it.trimMargin()}" }
    }

    fun setTables(vararg tables: String) {
        val _tables = tables.joinToString(prefix = "\n", separator = ",\n", postfix = "") {
            ("|   $it")
        }
        from = """
            |$FROM${_tables.trimEnd()}"""
    }

    fun addJoin(join: String) {
        _joins.add(join)
    }

    fun addWhere(rowFilter: String) {
        where = """
            |$WHERE
            |   $rowFilter"""
    }

    fun addGroupBy(vararg expr: String) {
        groupBy = """|$GROUP_BY
                     |   ${expr.joinToString(",\n   ")}"""
    }

    fun addHaving(expr: String) {
        having = """|$HAVING
                    |   $expr"""
    }

    fun addOrderBy(vararg orderingTerms: String) {
        orderBy = """
            |$ORDER_BY 
            |   ${orderingTerms.joinToString(",\n   ")}"""
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
