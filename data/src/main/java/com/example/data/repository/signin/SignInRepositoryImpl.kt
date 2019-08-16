package com.example.data.repository.signin

import com.example.data.database.AppDatabase
import com.example.data.database.dao.UserDao
import com.example.domian.entities.User
import com.example.domian.repositories.signin.SignInRepository

class SignInRepositoryImpl(database: AppDatabase) : SignInRepository {

    private val dao: UserDao = database.userDao()

    override fun validateUserExist(username: String?, isUserExist: (User?) -> Unit) {
        isUserExist(dao.isUserExist(username))
    }

    override fun insertNewUser(user: User, response: (Boolean) -> Unit) {
        var res: Long = dao.insertUser(user)
        response(
            when (res) {
                0L -> false
                else -> true
            }
        )
    }
}