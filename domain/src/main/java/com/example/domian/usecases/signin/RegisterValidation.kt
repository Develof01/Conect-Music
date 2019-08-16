package com.example.domian.usecases.signin

import com.example.domian.entities.User
import com.example.domian.repositories.signin.SignInRepository

class RegisterValidation(private val repository: SignInRepository) {

    fun registerUser(username: String?, pwd: String?, email: String?): Boolean {
        var isRegistered = false
        repository.validateUserExist(username) {
            if (it == null) {
                repository.insertNewUser(User(null, username, pwd, email)) {
                    isRegistered = it
                }
            }
        }
        return isRegistered
    }
}