package com.example.conct_music.view.favorites

import com.example.data.repository.favoritees.FavoriteRepositoryImpl
import com.example.domian.usecases.favorite.FavoriteTrack
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object FavoriteModule {

    fun getFavoriteModule() : Module {
        return module { viewModel { FavoriteViewModel(FavoriteTrack(FavoriteRepositoryImpl(get())), get()) } }
    }

}