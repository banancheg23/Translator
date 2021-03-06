package com.banancheg.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 2)
abstract class TranslatorDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}