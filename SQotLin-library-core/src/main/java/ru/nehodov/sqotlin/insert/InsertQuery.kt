package ru.nehodov.sqotlin.insert

class InsertQuery(private val table: String) {

    private var columns: List<String> = listOf()
    private var values: List<String> = listOf()

    fun setColumns(vararg _columns: String) {
        columns = _columns.toList()
    }

    fun setValues(vararg _values: Any) {
        values = _values.map { value -> value.toString() }
    }

    fun query(): String {
//        compareColumnsAndValuesSizes()
        val query = StringBuilder("|INSERT INTO $table (\n")
        val _columns = StringBuilder()
        columns.forEach { column -> _columns.append("|       $column,\n") }
        query.append(_columns.trimEnd().trimEnd(',')).append("\n)")
        val _values = StringBuilder("\n|VALUES (\n")
        values.forEach { value -> _values.append("|       $value,\n") }
        query.append(_values.trimEnd().trimEnd(',')).append("\n)")
        return query.toString().trimMargin()
    }

    private fun compareColumnsAndValuesSizes() {
        if (columns.size != values.size) {
            val errorMsg = "Number of columns(${columns.size}) differs from number of values(${values.size})"
            throw ColumnsAndValuesSizesNotEqualException(errorMsg)

        }
    }

    override fun toString(): String = query()


}