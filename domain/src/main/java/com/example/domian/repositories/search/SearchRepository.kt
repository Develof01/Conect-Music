package com.example.domian.repositories.search

import com.example.domian.entities.MusicTrack
import com.example.domian.entities.TrackInformation
import retrofit2.Response

interface SearchRepository {
    suspend fun searchSingers(singer: String?): Response<MusicTrack>?

    fun validateUserTrack(idUser: Int?, idTrack: Int?, track: (TrackInformation?) -> Unit)
    fun addTrackFavorite(track: TrackInformation, result:(Boolean) -> Unit)

}