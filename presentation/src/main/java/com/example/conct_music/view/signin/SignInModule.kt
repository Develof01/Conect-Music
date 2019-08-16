package com.example.conct_music.view.signin

import com.example.data.repository.signin.SignInRepositoryImpl
import com.example.domian.usecases.signin.RegisterValidation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object SignInModule {

    fun getSignInModule() : Module {
        return module { viewModel { SignInViewModel(get(), RegisterValidation(SignInRepositoryImpl(get()))) } }
    }

} 