package com.danielvilha.codelabsandroidroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by danielvilha on 2019-06-07
 */
const val DB_NAME = "Word_database"
const val DB_VERSION = 1

@Database(entities = [Word::class], version = DB_VERSION)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    DB_NAME
                ).addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { db ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(db.wordDao())
                }
            }
        }

        private fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()

            scope.launch {
                var word = Word("Hello")
                wordDao.insert(word)
                word = Word(("World"))
                wordDao.insert(word)
            }
        }
    }
}