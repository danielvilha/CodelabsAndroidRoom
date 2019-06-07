package com.danielvilha.codelabsandroidroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by danielvilha on 2019-06-07
 */
@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)