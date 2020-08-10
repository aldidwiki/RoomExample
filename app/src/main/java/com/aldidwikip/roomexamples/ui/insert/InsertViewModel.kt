package com.aldidwikip.roomexamples.ui.insert

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.aldidwikip.roomexamples.data.model.Word
import com.aldidwikip.roomexamples.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertViewModel(application: Application) : BaseViewModel(application) {

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}