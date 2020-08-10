package com.aldidwikip.roomexamples.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aldidwikip.roomexamples.data.model.Word
import com.aldidwikip.roomexamples.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {
    val allWords: LiveData<List<Word>> = repository.allWords

    fun delete(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }
}