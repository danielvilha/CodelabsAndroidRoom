package com.danielvilha.codelabsandroidroom

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 * Created by danielvilha on 2019-06-07
 */
class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}