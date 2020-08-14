package com.aldidwikip.roomexamples.ui.insert

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldidwikip.roomexamples.data.WordRepository
import com.aldidwikip.roomexamples.data.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertViewModel @ViewModelInject constructor(private val repository: WordRepository) : ViewModel() {

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}