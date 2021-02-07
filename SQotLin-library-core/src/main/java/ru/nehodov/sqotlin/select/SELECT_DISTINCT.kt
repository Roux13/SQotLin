package ru.nehodov.sqotlin.select

class SELECT_DISTINCT(
    vararg columns: String
): SELECT(*columns, isDistinct = true), ISelect