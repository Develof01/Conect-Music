package com.example.domian.repositories.login

import com.example.domian.entities.User

interface LoginRepository {
    fun validateUser(username: String?, pwd: String?, user: (User?) -> Unit)
}