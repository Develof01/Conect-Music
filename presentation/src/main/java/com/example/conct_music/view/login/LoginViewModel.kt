package com.example.conct_music.view.login

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conct_music.R
import com.example.conct_music.utils.validateEmptyField
import com.example.domian.entities.User
import com.example.domian.usecases.login.UserValidator

class LoginViewModel(val context: Context, private val validator: UserValidator) : ViewModel() {

    var user = ObservableField("")
    var pwd = ObservableField("")

    var userError = MutableLiveData<String>()
    var pwdError = MutableLiveData<String>()

    var isProgressShow = ObservableField<Boolean>()

    var userResponse: MutableLiveData<User>? = MutableLiveData()

    init {
        isProgressShow.set(false)
    }

    fun startSession() {
        if (validateEmptyField(user.get(), userError, context.resources.getString(R.string.login_user_empty)) &&
            validateEmptyField(pwd.get(), pwdError, context.resources.getString(R.string.login_pwd_empty))
        ) {
            validator.validateUser(user.get(), pwd.get()) {
                userResponse?.value = it
            }
        }
    }


}

