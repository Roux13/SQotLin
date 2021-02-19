package ru.nehodov.sqotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TestOpenHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {

    private val table_name = "table_name"
    private val column_a = "column_a"
    private val column_b = "column_b"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE $table_name(
                 $column_a VARCHAR,
                 $column_b VARCHAR
            )
        """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}