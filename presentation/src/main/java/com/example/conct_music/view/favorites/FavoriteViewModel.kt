package com.example.conct_music.view.favorites

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domian.entities.TrackInformation
import com.example.domian.entities.User
import com.example.domian.usecases.favorite.FavoriteTrack

class FavoriteViewModel(private val favoriteTrack: FavoriteTrack, private val user: User) : ViewModel(), FavoriteContract.ViewModel {

    var tracks: MutableLiveData<List<TrackInformation>>? = MutableLiveData()
    var isProgressShow = ObservableField<Boolean>()
    val isTrackDelete = MutableLiveData<Boolean>()

    init {
        isProgressShow.set(false)
        getFavoriteTracks()
    }

    override fun getFavoriteTracks() {
        isProgressShow.set(true)
        favoriteTrack.getFavoriteTracks(user.idUser!!.toInt()) {
            tracks?.value = it
        }
    }

    override fun deleteFavoriteTrack(track: TrackInformation?) {
        isTrackDelete.value = favoriteTrack.deleteTrack(track)
    }

}