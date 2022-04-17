package com.banancheg.repository.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

private const val COLUMN_WORD = "word"
private const val COLUMN_DESCRIPTION = "description"
private const val COLUMN_TRANSLATION = "translation"
private const val COLUMN_IMAGE_URL = "imageUrl"


@Entity(indices = [Index(value = arrayOf(COLUMN_WORD), unique = true)])
class HistoryEntity (
    @PrimaryKey
    @ColumnInfo(name = COLUMN_WORD) var word: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION) var description: String?,
    @ColumnInfo(name = COLUMN_TRANSLATION) var translation: String?,
    @ColumnInfo(name = COLUMN_IMAGE_URL) var imageUrl: String?
    )

