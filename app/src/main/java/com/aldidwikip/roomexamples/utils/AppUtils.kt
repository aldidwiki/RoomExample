package com.aldidwikip.roomexamples.utils

import android.widget.EditText

object AppUtils {

    fun EditText.showErrorMessage() {
        this.error = "Value must not empty"
    }
}
