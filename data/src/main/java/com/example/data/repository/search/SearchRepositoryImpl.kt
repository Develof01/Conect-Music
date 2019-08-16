package com.example.data.repository.search

import android.util.Log
import com.example.data.database.AppDatabase
import com.example.data.database.dao.TrackDao
import com.example.data.interactor.WsInteractor
import com.example.domian.entities.MusicTrack
import com.example.domian.entities.TrackInformation
import com.example.domian.repositories.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SearchRepositoryImpl(private val interactor: WsInteractor, private val database: AppDatabase) : SearchRepository {

    var generalReponse: Response<MusicTrack>? = null
    private val dao: TrackDao = database.trackDao()

    override suspend fun searchSingers(singer: String?): Response<MusicTrack>? {
        val response = interactor.getSingers(singer!!, "musicTrack")
        try {
            withContext(Dispatchers.Main) {
                generalReponse = response.await()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e("Itunes error", e.message)
            }
        }
        return generalReponse
    }


    override fun validateUserTrack(idUser: Int?, idTrack: Int?, track: (TrackInformation?) -> Unit) {
        track(dao.userHaveTrack(idUser, idTrack))
    }

    override fun addTrackFavorite(track: TrackInformation, result: (Boolean) -> Unit) {

        var res: Long = dao.insertUserTrack(track)
        result(
            when (res) {
                0L -> false
                else -> true
            }
        )
    }

}