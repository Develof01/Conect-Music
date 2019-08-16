package com.example.data.repository.login

import com.example.data.database.AppDatabase
import com.example.data.database.dao.UserDao
import com.example.domian.repositories.login.LoginRepository
import com.example.domian.entities.User

class LoginRepositoryImpl(database: AppDatabase): LoginRepository {

    private val dao: UserDao = database.userDao()

    override fun validateUser(username: String?, pwd: String?, user: (User?) -> Unit) {
        user(dao.validateUser(username, pwd))
    }

}