package ru.nehodov.sqotlin.select

class CrossJoin(query: SelectQuery, table: String) : Join(query, "CROSS JOIN", table)