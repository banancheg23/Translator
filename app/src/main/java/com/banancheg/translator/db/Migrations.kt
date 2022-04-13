package com.banancheg.translator.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE HistoryEntity ADD COLUMN translation text")
        database.execSQL("ALTER TABLE HistoryEntity ADD COLUMN imageUrl text")
    }

}