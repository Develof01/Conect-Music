package com.example.domian.repositories.signin

import com.example.domian.entities.User

interface SignInRepository {

    fun validateUserExist(username: String?, user: (User?) -> Unit)
    fun insertNewUser(user: User, result:(Boolean) -> Unit)
}