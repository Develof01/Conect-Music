package com.example.conct_music.view.search

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domian.entities.TrackInformation
import com.example.domian.entities.User
import com.example.domian.usecases.search.StartSearchSingers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchViewModel(private val search: StartSearchSingers,  private val user: User) : ViewModel(), SearchContract.ViewModel {

    var tracks: MutableLiveData<List<TrackInformation>>? = MutableLiveData()
    var isProgressShow = ObservableField<Boolean>()
    var isEmptyState = ObservableField<Boolean>()

    var isUserTrackCreated = MutableLiveData<Boolean>()

    init {
        isProgressShow.set(false)
        isEmptyState.set(true)
    }

    override suspend fun startSearch(text: String) {
        isEmptyState.set(false)
        isProgressShow.set(true)
        search.starSearch(text) {
            tracks?.value = it?.results
        }
    }

    override fun createFavoriteTrack(track: TrackInformation?) {
        track?.userId = user.idUser!!.toInt()
        isUserTrackCreated.value = search.addUserTrack(track)
    }

}