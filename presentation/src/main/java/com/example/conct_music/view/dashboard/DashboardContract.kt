package com.example.conct_music.view.dashboard

import com.example.domian.entities.TrackInformation

interface DashboardContract {

    interface View {
        fun initDependences()
        fun getListData(tracks: List<TrackInformation>?)
        fun getTrackPosition(position: Int)
        fun startSelectedTrack(urlTrack: String?)
        fun playOrPauseTrack()
        fun previous()
        fun next()
    }

}