package ru.nehodov.sqotlin.select

class SelectQuery {

    val columns = mutableListOf<String>()
    val tables = mutableListOf<String>()
    var from: From? = null
    val joins = mutableListOf<Join>()
    var where: Where? = null
    var orderBy: OrderBy? = null
    var limit: Limit? = null
    var groupBy: GroupBy? = null
    var having: Having? = null

    fun sql(): String {
        return """
            |SELECT(
            |$SPACES${embedColumnList()}
            |$SPACES)
        """.trimMargin()
    }

    fun embedColumnList(): String {
        val sb = StringBuilder()
        columns.forEach {
            sb.append("$SPACES$it,").appendLine()
        }
        return sb.toString().trim().trimEnd(',')
//        return columns.joinToString(prefix = "\t", postfix = ",\n").trimEnd('\n').trimEnd(',')
    }

    companion object {
        const val SELECT = "SELECT"
        const val FROM = "FROM"
        const val WHERE = "WHERE"
        const val ORDER_BY = "ORDER BY"
        const val LIMIT = "LIMIT"
        const val SPACES = "   "
    }
}