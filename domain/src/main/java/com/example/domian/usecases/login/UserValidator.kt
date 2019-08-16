package com.example.domian.usecases.login

import com.example.domian.entities.User
import com.example.domian.repositories.login.LoginRepository

class UserValidator(private val repository: LoginRepository) {


    fun validateUser(username: String?, pwd: String?, user: (User?) -> Unit) {
        return repository.validateUser(username, pwd, user)
    }

}