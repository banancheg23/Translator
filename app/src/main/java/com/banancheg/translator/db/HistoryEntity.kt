package com.banancheg.translator.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.banancheg.translator.model.data.Meanings

@Entity(indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryEntity (
    @PrimaryKey
    @ColumnInfo(name = "word") var word: String,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "translation") var translation: String?,
    @ColumnInfo(name = "imageUrl") var imageUrl: String?
    )

