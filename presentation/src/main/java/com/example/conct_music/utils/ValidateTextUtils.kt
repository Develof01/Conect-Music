package com.example.conct_music.utils

import android.util.Patterns
import androidx.lifecycle.MutableLiveData


fun validateEmptyField(
    field: String?,
    fieldError: MutableLiveData<String>,
    msgError: String
): Boolean {
    fieldError.value = if (field!!.isEmpty()) msgError else null
    return fieldError.value == null
}

fun validatePwdField(
    field: String?,
    fieldError: MutableLiveData<String>,
    msgError: String
): Boolean {
    val pattern = Const.RegularExpresion.PWD_RE.toRegex()
    fieldError.value = if (field!!.isEmpty() || !pattern.matches(field.toString())) msgError else null
    return fieldError.value == null
}

fun validateEmailFiled(
    field: String?,
    fieldError: MutableLiveData<String>,
    msgError: String
): Boolean {
    fieldError.value = if (field!!.isValidEmail()) null else msgError
    return fieldError.value == null
}


private fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()