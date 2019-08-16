package com.example.conct_music.view.login

import com.example.data.repository.login.LoginRepositoryImpl
import com.example.domian.entities.User
import com.example.domian.usecases.login.UserValidator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginModule {

    fun getLoginModule() : Module {
        return module { viewModel { LoginViewModel(get(), UserValidator(LoginRepositoryImpl(get()))) } }
    }


    fun loadUserModule(user: User) : Module {
        return module { factory { user } }
    }

}