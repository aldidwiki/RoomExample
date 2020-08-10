package com.aldidwikip.roomexamples.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aldidwikip.roomexamples.data.WordRepository
import com.aldidwikip.roomexamples.data.room.WordRoomDatabase

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val repository: WordRepository

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
    }
}