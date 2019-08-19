package com.example.data.repository.favoritees

import androidx.lifecycle.LiveData
import com.example.data.database.AppDatabase
import com.example.data.database.dao.TrackDao
import com.example.domian.entities.TrackInformation
import com.example.domian.repositories.favorites.FavoriteRepository

class FavoriteRepositoryImpl(database: AppDatabase) : FavoriteRepository {
    private val dao: TrackDao = database.trackDao()

    override fun getUserTrack(userId: Int?): LiveData<List<TrackInformation>?> {
        return dao.getUserTracks(userId)
    }


    override fun deleteTrack(userTrack: TrackInformation?, result: (Boolean) -> Unit) {
        dao.delete(userTrack)
        result(true)
    }

}