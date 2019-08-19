package com.example.conct_music.view.favorites

import com.example.domian.entities.TrackInformation


interface FavoriteContract {

    interface View {
        fun initDependences()
        fun startTrack(urlTrack: String)
        fun getTrackPosition(position: Int)
    }

    interface ViewModel {
         fun getFavoriteTracks()
        fun deleteFavoriteTrack(track: TrackInformation?)
    }

}