package com.example.conct_music.view.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conct_music.R
import com.example.conct_music.adapters.FavoriteAdapter
import com.example.conct_music.databinding.FragmentFavoritesBinding
import com.example.domian.entities.TrackInformation
import com.example.domian.entities.User
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoritesFragment : Fragment(), FavoriteContract.View {


    private val isPresenterLoad = getKoin().getAll<FavoriteViewModel>()
    private val loadFeatures by lazy { loadKoinModules(FavoriteModule.getFavoriteModule()) }

    val viewModel: FavoriteViewModel by viewModel()

    lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        if (isPresenterLoad.isEmpty()) initDependences()

        binding.favorite = this
        binding.viewModel = viewModel

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rvMusicTracks.setHasFixedSize(true)
        binding.rvMusicTracks.layoutManager = linearLayoutManager
        val adapter = FavoriteAdapter(this)
        binding.rvMusicTracks.adapter = adapter


        viewModel.tracks?.observe(this, Observer<List<TrackInformation>> {
            viewModel.isProgressShow.set(false)
            adapter.setTracksMusicInfo(it)
        })

        viewModel.isTrackDelete.observe(this, Observer<Boolean> {
            if (it) {
                Toast.makeText(activity, resources.getString(R.string.singers_track_deleted), Toast.LENGTH_LONG).show()
                viewModel.getFavoriteTracks()  //TODO CAMBIAR HARCODE
            }
        })

    }


    override fun initDependences() = loadFeatures

}
