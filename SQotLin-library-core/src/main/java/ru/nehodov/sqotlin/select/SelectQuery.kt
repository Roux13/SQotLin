package ru.nehodov.sqotlin.select

class SelectQuery(private val isDistinct: Boolean) {

    private var columns: String = ""
    private var from: String? = null
    private var joins: MutableList<JoinContainer>? = null
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
        columns.let { query.append(columns) }
        from?.let { query.append("$from") }
        joins?.let { query.append(appendJoins()) }
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
            if (column.isNotEmpty()) {
                val currentColumn = StringBuilder()
                column.trimMargin()
                    .split("\n")
                    .forEach { line ->
                        currentColumn.append("|   $line\n")
                    }
                _columns.append(currentColumn.trimEnd()).append(",\n")
            }

        }
        this.columns = _columns.toString().trimEnd().trimEnd(',').trimEnd()
    }

    fun setTables(vararg tables: String) {
        val _from = StringBuilder(
            """
            |$FROM
            """
        )
        tables.forEach { table ->
            if (table.isNotEmpty()) {
                val currentTable = StringBuilder()
                table.trimMargin()
                    .split("\n")
                    .forEach { line ->
                        currentTable.append("|   $line\n")
                    }
                _from.append(currentTable.trimEnd()).append(",\n")
            }
        }
        from = _from.toString().trimEnd().trimEnd(',').trimEnd()
    }

    fun addJoin(joinContainer: JoinContainer) {
        if (joins == null) {
            joins = mutableListOf()
        }
        joins?.add(joinContainer)
    }

    private fun appendJoins(): String {
        val _joins = StringBuilder()
        joins?.forEach { joinContainer ->
            val currentTable = StringBuilder("\n|${joinContainer.joinType}")
            val lines = joinContainer.table.trimMargin().split("\n")
            for (i in lines.indices) {
                if (i == 0) {
                    currentTable.append(" ${lines[i]}\n")
                } else {
                    currentTable.append("|   ${lines[i]}\n")
                }
            }
            _joins.append("$currentTable")
                .append("|   ON ${joinContainer.joinCondition}")
        }
        return _joins.toString().trimEnd()
    }

    fun addWhere(rowFilter: String) {
        where = """
            |$WHERE
            |   $rowFilter"""
    }

    fun addGroupBy(vararg columns: String) {
        val _groupBy = StringBuilder("\n|$GROUP_BY\n")
        columns.forEach { column ->
            if (column.isNotEmpty()) {
                _groupBy.append("|   $column,\n")
            }
        }
        groupBy = _groupBy.toString().trimEnd().trimEnd(',').trimEnd()
    }

    fun addHaving(expr: String) {
        having = """
            |$HAVING
            |   $expr
        """
    }

    fun addOrderBy(vararg orderingTerms: String) {
        orderBy = """
            |$ORDER_BY 
            |   ${orderingTerms.joinToString(",\n   ")}"""
    }

    fun addLimit(rowCount: Int) {
        this.limit = """|
            |$LIMIT $rowCount
        """.trimMargin()
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
