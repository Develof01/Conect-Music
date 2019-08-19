package com.example.conct_music.adapters

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.conct_music.R
import com.example.conct_music.databinding.AdapterTrackMusicBinding
import com.example.conct_music.extensions.setImageUrl
import com.example.conct_music.view.favorites.FavoritesFragment
import com.example.domian.entities.TrackInformation
import kotlinx.android.synthetic.main.adapter_track_music.view.*

class FavoriteAdapter(val context: FavoritesFragment, private val mInflater: LayoutInflater = LayoutInflater.from(context.context)) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    lateinit var binding: AdapterTrackMusicBinding

    private var tracks: List<TrackInformation>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        binding = DataBindingUtil.inflate(mInflater, R.layout.adapter_track_music, parent, false)
        return FavoriteHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return if (tracks != null) tracks!!.size
        else 0
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        val track: TrackInformation = tracks!![position]
        holder.itemView.tv_track_name.text = track.trackName
        holder.itemView.tv_artist_name.text = track.artistName
        holder.itemView.tv_collection_name.text = track.collectionName
        holder.itemView.iv_track.setImageUrl((track.artworkUrl100))

        holder.itemView.iv_favorite_track.setImageDrawable(context.resources.getDrawable(R.drawable.ic_favorite))


        holder.itemView.iv_favorite_track.setOnClickListener {
            (context).viewModel.deleteFavoriteTrack(track)
        }

        holder.itemView.cv_artist.setOnClickListener {
            (context).startTrack(track.previewUrl!!)
            (context).getTrackPosition(position)
        }
    }

    internal fun setTracksMusicInfo(tracks: List<TrackInformation>?) {
        this.tracks = tracks
        notifyDataSetChanged()
    }


    class FavoriteHolder(v: View) : RecyclerView.ViewHolder(v)
}