package com.aldidwikip.roomexamples.utils

import android.widget.EditText
import com.aldidwikip.roomexamples.utils.Constant.ERROR_MESSAGE

object AppUtils {

    fun EditText.showErrorMessage() {
        this.error = ERROR_MESSAGE
    }
}
