package ru.nehodov.sqotlin.select

open class Join(incomingQuery: String, joinClause: String, table: String): ISelect {

    private var query: String

    init {
        query = """
            |$incomingQuery
            |   $joinClause $table
            """.trimMargin()
    }

    infix fun ON(joinCondition: String): Join {
        query = """
            |$query $ON $joinCondition
            """.trimMargin()
        return this
    }

    fun INNER_JOIN(table: String): Join {
        return InnerJoin(this, table)
    }

    fun CROSS_JOIN(table: String): Join {
        return CrossJoin(this, table)
    }

    fun LEFT_JOIN(table: String): Join {
        return LeftJoin(this, table)
    }

    fun WHERE(rowFilter: String): Where {
        return Where(this, rowFilter)
    }

    override fun sql() = query

    companion object {
        private const val INNER_JOIN = "INNER JOIN"
        private const val CROSS_JOIN = "INNER JOIN"
        private const val LEFT_JOIN = "INNER JOIN"
        private const val FROM = "FROM"
        private const val ON = "ON"
        private const val USING = "USING"
    }

   enum class JoinType {
       INNER_JOIN_TYPE,
       CROSS_JOIN_TYPE,
       LEFT_JOIN_TYPE
   }
}