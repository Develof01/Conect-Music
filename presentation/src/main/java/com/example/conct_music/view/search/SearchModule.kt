package com.example.conct_music.view.search

import com.example.data.repository.search.SearchRepositoryImpl
import com.example.domian.usecases.search.StartSearchSingers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object SearchModule {

    fun getSearchModule() : Module {
        return module { viewModel { SearchViewModel(StartSearchSingers(SearchRepositoryImpl(get(), get())), get()) } }
    }

}