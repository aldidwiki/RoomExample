package com.aldidwikip.roomexamples.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aldidwikip.roomexamples.data.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        suspend fun populateDatabase(wordDao: WordDao) {
            // Add sample words.
            var word = Word(0, "Dave", "Male", "Chef", "Delhi")
            wordDao.insert(word)
            word = Word(0, "Simon", "Male", "Police", "New York City")
            wordDao.insert(word)
        }
    }
}