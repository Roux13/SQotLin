package ru.nehodov.sqotlin.select

open class Join(private val query: SelectQuery, joinType: String, table: String): ISelect {

    init {
        query.addJoin("$joinType $table")
//            query = """
//            |$incomingQuery
//            |   $joinType $table
//            """.trimMargin()
    }

//        infix fun ON(joinCondition: String): Join {
//            query = """
//            |$query "ON" $joinCondition
//            """.trimMargin()
//            return this
//        }

    fun INNER_JOIN(table: String): Join {
        return InnerJoin(query, table)
    }

    fun CROSS_JOIN(table: String): Join {
        return CrossJoin(query, table)
    }

    fun LEFT_JOIN(table: String): Join {
        return LeftJoin(query, table)
    }

    fun WHERE(rowFilter: String): Where {
        return Where(query, rowFilter)
    }

    override fun sql(): String = query.sql()
}