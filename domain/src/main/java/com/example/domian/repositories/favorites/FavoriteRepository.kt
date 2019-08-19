package com.example.domian.repositories.favorites

import com.example.domian.entities.TrackInformation
import androidx.lifecycle.LiveData

interface FavoriteRepository {


    fun getUserTrack(userId: Int?): LiveData<List<TrackInformation>?>
    fun deleteTrack(userTrack: TrackInformation?, result:(Boolean) -> Unit)

}