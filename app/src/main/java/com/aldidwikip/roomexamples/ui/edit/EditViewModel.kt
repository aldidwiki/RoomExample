package com.aldidwikip.roomexamples.ui.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.data.WordRepository
import com.aldidwikip.roomexamples.data.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel @ViewModelInject constructor(private val repository: WordRepository) : ViewModel() {
    private val _name = MutableLiveData<String>()
    private val _gender = MutableLiveData<String>()
    private val _job = MutableLiveData<String>()
    private val _city = MutableLiveData<String>()

    val name: LiveData<String> = _name
    val gender: LiveData<String> = _gender
    val job: LiveData<String> = _job
    val city: LiveData<String> = _city

    fun update(id: Int, name: String, gender: String, job: String, city: String) =
            viewModelScope.launch(Dispatchers.IO) {
                repository.update(id, name, gender, job, city)
            }

    fun setId(id: Int) = repository.getWord(id)

    fun setWord(data: Word) {
        _name.postValue(data.name)
        _gender.postValue(data.gender)
        _job.postValue(data.job)
        _city.postValue(data.city)
    }

    val checkedButton: LiveData<RadioButtonId> = Transformations.map(_gender) {
        if (it == "Male") RadioButtonId.RB_MALE_ID else RadioButtonId.RB_FEMALE_ID
    }

    enum class RadioButtonId(val value: Int) {
        RB_MALE_ID(R.id.rb_male),
        RB_FEMALE_ID(R.id.rb_female)
    }
}