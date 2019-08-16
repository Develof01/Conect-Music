package com.example.domian.usecases.search

import com.example.domian.entities.MusicTrack
import com.example.domian.entities.TrackInformation
import com.example.domian.repositories.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartSearchSingers(private val repository: SearchRepository) {

    fun starSearch(singers: String, response: (MusicTrack?) -> Unit ) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                response(repository.searchSingers(singers)?.body())
            }
        }
    }

    fun addUserTrack(track: TrackInformation?): Boolean {
        var isRegistered = false
        repository.validateUserTrack(track?.userId, track?.trackId) {
            if (it == null) {
                if (track != null) {
                    repository.addTrackFavorite(track) {
                        isRegistered = it
                    }
                }
            }
        }
        return isRegistered
    }

}