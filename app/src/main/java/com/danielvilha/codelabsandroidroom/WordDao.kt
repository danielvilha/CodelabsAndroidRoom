package com.danielvilha.codelabsandroidroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by danielvilha on 2019-06-07
 */
@Dao
interface WordDao {
    @Query("select * from word_table order by word asc")
    fun getAllWords(): LiveData<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Query("delete from word_table")
    fun deleteAll()
}