package com.aldidwikip.roomexamples.repository

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun delete(id: Int) {
        wordDao.delete(id)
    }

    suspend fun update(id: Int, name: String, job: String, city: String) {
        wordDao.update(id, name, job, city)
    }
}