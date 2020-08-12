package com.aldidwikip.roomexamples.utils

import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.ui.edit.EditViewModel.RadioButtonId

@BindingAdapter("app:checkedButton")
fun checkedButton(view: RadioGroup, rbId: RadioButtonId?) {
    val id = if (rbId == RadioButtonId.RB_MALE_ID) R.id.rb_male else R.id.rb_female
    view.check(id)
}