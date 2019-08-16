package com.example.domian.usecases.favorite

import com.example.domian.entities.TrackInformation
import com.example.domian.repositories.favorites.FavoriteRepository

class FavoriteTrack(private val repository: FavoriteRepository) {

    fun getFavoriteTracks(userId: Int?, response: (List<TrackInformation>?) -> Unit) {
        response(repository.getUserTrack(userId))
    }


    fun deleteTrack(userTrack: TrackInformation?): Boolean {
        var isRegistered = false
        repository.deleteTrack(userTrack) {
            isRegistered = it
        }
        return isRegistered
    }

}