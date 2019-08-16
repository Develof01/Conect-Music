package com.example.domian.repositories.favorites

import com.example.domian.entities.TrackInformation

interface FavoriteRepository {

    fun getUserTrack(userId: Int?): List<TrackInformation>?
    fun deleteTrack(userTrack: TrackInformation?, result:(Boolean) -> Unit)

}