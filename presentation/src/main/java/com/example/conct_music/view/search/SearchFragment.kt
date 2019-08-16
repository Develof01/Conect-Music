package com.example.conct_music.view.search

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
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.example.conct_music.R
import com.example.conct_music.adapters.TrackMusicAdapter
import com.example.conct_music.databinding.FragmentSearchBinding
import com.example.domian.entities.TrackInformation
import com.example.domian.entities.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchFragment : Fragment(), SearchContract.View, FloatingSearchView.OnSearchListener {

    private val isPresenterLoad = getKoin().getAll<SearchViewModel>()
    private val loadFeatures by lazy { loadKoinModules(SearchModule.getSearchModule()) }

    val searchViewModel: SearchViewModel by viewModel()

    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        if (isPresenterLoad.isEmpty()) initDependences()

        binding.searchFragment = this
        binding.viewModel = searchViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rvMusicTracks.setHasFixedSize(true)
        binding.rvMusicTracks.layoutManager = linearLayoutManager
        val adapter = TrackMusicAdapter(this)
        binding.rvMusicTracks.adapter = adapter

        searchViewModel.tracks?.observe(this, Observer<List<TrackInformation>> {
            if (it.isNotEmpty()) {
                searchViewModel.isEmptyState.set(false)
            } else {
                searchViewModel.isEmptyState.set(true)
            }
            searchViewModel.isProgressShow.set(false)
            adapter.setTracksMusicInfo(it)
        })

        binding.svSinger.setOnSearchListener(this)


        searchViewModel.isUserTrackCreated.observe(this, Observer<Boolean> {
            if (it) {
                Toast.makeText(activity, resources.getString(R.string.singers_track_created), Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onSearchAction(currentQuery: String?) {
        GlobalScope.launch {
            searchViewModel.startSearch(currentQuery!!)
        }
        binding.svSinger.clearQuery()
    }

    override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {}


    override fun initDependences() = loadFeatures


}
