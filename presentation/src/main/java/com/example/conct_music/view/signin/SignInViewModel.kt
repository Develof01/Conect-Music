package com.example.conct_music.view.signin

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conct_music.R
import com.example.conct_music.extensions.validateEmailFiled
import com.example.conct_music.extensions.validateEmptyField
import com.example.conct_music.extensions.validatePwdField
import com.example.domian.usecases.signin.RegisterValidation

class SignInViewModel(val context: Context, private val registerValidation: RegisterValidation) : ViewModel() {

    var user = ObservableField("")
    var pwd = ObservableField("")
    var email = ObservableField("")

    var userError = MutableLiveData<String>()
    var pwdError = MutableLiveData<String>()
    var emailError = MutableLiveData<String>()

    var isUserCreated = MutableLiveData<Boolean>()

    fun registerUser() {
        if (validateEmptyField(
                user.get(),
                userError,
                context.resources.getString(R.string.invalid_user)
            ) &&
            validatePwdField(
                pwd.get(),
                pwdError,
                context.resources.getString(R.string.invalid_pwd)
            )
        ) {
            if (validateEmailFiled(
                    email.get(),
                    emailError,
                    context.resources.getString(R.string.invalid_email)
                )
            ) {
                isUserCreated.value = registerValidation.registerUser(user.get(), pwd.get(), email.get())
            }
        }
    }

}