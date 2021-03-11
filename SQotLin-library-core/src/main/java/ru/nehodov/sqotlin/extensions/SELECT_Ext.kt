package ru.nehodov.sqotlin.extensions

import ru.nehodov.sqotlin.select.ISelect
import ru.nehodov.sqotlin.select.Union

infix fun ISelect.UNION_ALL(other: ISelect): Union {
    return Union("""
        |${this.query()}
        |UNION ALL
        |${other.query()}
    """)
}

infix fun String.UNION_ALL(other: ISelect): Union {
    return Union("""
        |${this}
        |UNION ALL
        |${other.query()}
    """)
}

infix fun ISelect.UNION(other: ISelect): Union {
    return Union("""
        |${this.query()}
        |UNION
        |${other.query()}
    """)
}

infix fun String.UNION(other: ISelect): Union {
    return Union("""
        |${this}
        |UNION
        |${other.query()}
    """)
}

infix fun ISelect.UNION_ALL(other: String): Union {
    return Union("""
        |${this.query()}
        |UNION ALL
        |$other
    """)
}

infix fun String.UNION_ALL(other: String): Union {
    return Union("""
        |$this
        |UNION ALL
        |$other
    """)
}

infix fun ISelect.UNION(other: String): Union {
    return Union("""
        |${this.query()}
        |UNION
        |$other
    """)
}

infix fun String.UNION(other: String): Union {
    return Union("""
        |$this
        |UNION
        |$other
    """)
}