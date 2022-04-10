package com.banancheg.translator.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = true)
abstract class TranslatorDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}