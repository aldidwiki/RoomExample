package com.aldidwikip.roomexamples.ui.edit

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.aldidwikip.roomexamples.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(application: Application) : BaseViewModel(application) {

    fun update(id: Int, name: String, gender: String, job: String, city: String) =
            viewModelScope.launch(Dispatchers.IO) {
                repository.update(id, name, gender, job, city)
            }
}