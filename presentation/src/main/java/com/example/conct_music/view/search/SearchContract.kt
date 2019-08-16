package com.example.conct_music.view.search

import com.example.domian.entities.TrackInformation

interface SearchContract {

    interface View {
        fun initDependences()
    }

    interface ViewModel {
        suspend fun startSearch(text: String)
        fun createFavoriteTrack(track: TrackInformation?)
    }

}