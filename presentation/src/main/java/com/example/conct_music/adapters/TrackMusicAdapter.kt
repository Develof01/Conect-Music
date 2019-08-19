package com.example.conct_music.adapters

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.conct_music.R
import com.example.conct_music.databinding.AdapterTrackMusicBinding
import com.example.conct_music.extensions.setImageUrlCoil
import com.example.domian.entities.TrackInformation
import kotlinx.android.synthetic.main.adapter_track_music.view.*
import com.example.conct_music.view.search.SearchFragment


class TrackMusicAdapter(val context: SearchFragment, private val mInflater: LayoutInflater = LayoutInflater.from(context.context)) :
    RecyclerView.Adapter<TrackMusicAdapter.TrackMusicHolder>() {

    lateinit var binding: AdapterTrackMusicBinding

    private var tracks: List<TrackInformation>? = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackMusicHolder {
        binding = DataBindingUtil.inflate(mInflater, R.layout.adapter_track_music, parent, false)
        binding.musicAdapter = this
        return TrackMusicHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return if (tracks != null) tracks!!.size
        else 0
    }

    override fun onBindViewHolder(holder: TrackMusicHolder, position: Int) {
        val track: TrackInformation = tracks!![position]
        holder.itemView.tv_track_name.text = track.trackName
        holder.itemView.tv_artist_name.text = track.artistName
        holder.itemView.tv_collection_name.text = track.collectionName
        holder.itemView.iv_track.setImageUrlCoil(track.artworkUrl100)

        holder.itemView.cv_artist.setOnClickListener {
            (context).startTrack(track.previewUrl!!)
            (context).getTrackPosition(position)
        }


        holder.itemView.iv_favorite_track.setOnClickListener{
            (context).searchViewModel.createFavoriteTrack(track)
        }
    }

    internal fun setTracksMusicInfo(tracks: List<TrackInformation>) {
        this.tracks = tracks
        notifyDataSetChanged()
    }



    class TrackMusicHolder(v: View) : RecyclerView.ViewHolder(v)

}