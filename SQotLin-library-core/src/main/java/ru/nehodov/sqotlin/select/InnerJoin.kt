package ru.nehodov.sqotlin.select

class InnerJoin(query: SelectQuery, table: String) : Join(query,"INNER_JOIN", table)