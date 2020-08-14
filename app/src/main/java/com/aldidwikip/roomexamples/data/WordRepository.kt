package com.aldidwikip.roomexamples.data

import androidx.lifecycle.LiveData
import com.aldidwikip.roomexamples.data.model.Word
import com.aldidwikip.roomexamples.data.room.WordDao
import javax.inject.Inject

class WordRepository @Inject constructor(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    fun getWord(id: Int): LiveData<Word> = wordDao.getWord(id)

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun delete(id: Int) {
        wordDao.delete(id)
    }

    suspend fun update(id: Int, name: String, gender: String, job: String, city: String) {
        wordDao.update(id, name, gender, job, city)
    }
}