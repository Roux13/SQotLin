package ru.nehodov.sqotlin.select

open class SELECT(
    vararg columns: String,
    isDistinct: Boolean = false,
) : ISelect {
    private val query: SelectQuery = SelectQuery(isDistinct)

    init {
        query.setColumns(*columns)
    }

    fun FROM(vararg tableList: String): From {
        return From(query, *tableList)
    }

//    fun FROM(subQuery: SELECT, AS: String = ""): From {
//        val alias = if (AS.isNotEmpty()) " $AS" else ""
//        return FROM(
//                query = """
//            |$query
//            |$FROM
//            |   $subQuery $alias
//            """.trimMargin()
//        )
//    }


//    fun GROUP_BY(clause: String): SELECT {
//        return SELECT(
//                """|$query
//                         |GROUP BY $clause
//                         """.trimMargin("|")
//        )
//    }

    override fun sql(): String = query.sql()
    override fun toString(): String {
        return query.sql()
    }

}






