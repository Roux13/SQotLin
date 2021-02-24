package ru.nehodov.sqotlin.select

class LeftJoin(query: SelectQuery, table: String) : Join(query,"LEFT JOIN", table)