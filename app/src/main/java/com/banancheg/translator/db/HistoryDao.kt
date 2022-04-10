package com.banancheg.translator.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    suspend fun getAllHistory(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE word=:word")
    suspend fun getDataByWord(word: String): HistoryEntity

    @Insert(onConflict = IGNORE)
    suspend fun insert(entity: HistoryEntity)

    @Insert(onConflict = IGNORE)
    suspend fun insertAll(entity: List<HistoryEntity>)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}