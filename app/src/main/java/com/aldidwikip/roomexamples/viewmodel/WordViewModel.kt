package com.aldidwikip.roomexamples.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aldidwikip.roomexamples.repository.Word
import com.aldidwikip.roomexamples.repository.WordRepository
import com.aldidwikip.roomexamples.repository.WordRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun delete(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }

    fun update(id: Int, name: String, job: String, city: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(id, name, job, city)
    }
}