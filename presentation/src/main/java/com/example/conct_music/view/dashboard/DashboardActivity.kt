package com.example.conct_music.view.dashboard

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.conct_music.R
import com.example.conct_music.databinding.ActivityDashboardBinding
import com.example.domian.entities.TrackInformation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class DashboardActivity : AppCompatActivity(), DashboardContract.View, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    lateinit var binding: ActivityDashboardBinding

    private val mediaPlayer: MediaPlayer by inject()
    private var tracks: List<TrackInformation>? = mutableListOf()

    private var positionTrack = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.dashboard = this

        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnCompletionListener(this)

        binding.navBottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_search -> {

                    if (Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination?.id == R.id.favoritesFragment) {
                        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.toSearchFragment)
                    }
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.menu_favorites -> {

                    if (Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination?.id == R.id.SearchFragment) {
                        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.toFavoritesFragment)
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            positionTrack = 0
            false
        }
    }

    override fun getListData(tracks: List<TrackInformation>?) {
        if (!tracks?.isEmpty()!!) {
            this.tracks = tracks
        }
    }

    override fun startSelectedTrack(urlTrack: String?) {
            if (mediaPlayer?.isPlaying) {
                mediaPlayer.setNextMediaPlayer(null)
                mediaPlayer.reset()
            }

            runBlocking {
                GlobalScope.launch {
                    try{
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(urlTrack)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                    } catch(ex: IllegalArgumentException) {
                        Log.e("Media Player Error", ex.message)
                    }

            }
        }
    }

    override fun getTrackPosition(position: Int) {
        this.positionTrack = position
    }


    override fun onPrepared(p0: MediaPlayer?) {
        binding.btnPlayPause.setImageDrawable(resources.getDrawable(R.drawable.ic_pause))
    }

    override fun onCompletion(p0: MediaPlayer?) {
        binding.btnPlayPause.setImageDrawable(resources.getDrawable(R.drawable.ic_play))
    }

    override fun playOrPauseTrack() {
        if (tracks?.isNotEmpty()!!) {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.btnPlayPause.setImageDrawable(resources.getDrawable(R.drawable.ic_play))
            } else {
                mediaPlayer.start()
                binding.btnPlayPause.setImageDrawable(resources.getDrawable(R.drawable.ic_pause))

            }
        }
    }

    override fun next() {
        if (tracks?.isNotEmpty()!!) {
            if (positionTrack == tracks?.size?.minus(1)) {
                positionTrack = 0
                startSelectedTrack(tracks?.get(positionTrack)?.previewUrl)
            } else {
                positionTrack += 1
                startSelectedTrack(tracks?.get(positionTrack)?.previewUrl)
            }
        }
    }

    override fun previous() {
        if (tracks?.isNotEmpty()!!) {
            if (positionTrack == 0) {
                positionTrack = 0
                startSelectedTrack(tracks?.get(positionTrack)?.previewUrl)
            } else {
                positionTrack -= 1
                startSelectedTrack(tracks?.get(positionTrack)?.previewUrl)
            }
        }
    }


    override fun initDependences() {

    }

}
