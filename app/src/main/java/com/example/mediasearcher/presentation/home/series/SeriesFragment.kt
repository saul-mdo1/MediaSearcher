package com.example.mediasearcher.presentation.home.series

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediasearcher.databinding.SeriesFragmentLayoutBinding
import com.example.mediasearcher.presentation.details.movies.MovieDetailsActivity
import com.example.mediasearcher.presentation.details.series.SeriesDetailsActivity
import com.example.mediasearcher.presentation.home.movies.MovieItemViewModel
import com.example.mediasearcher.presentation.home.movies.MoviesRVAdapter
import com.example.mediasearcher.presentation.home.movies.MoviesViewModel
import com.example.mediasearcher.utils.MOVIE_ID_TAG
import com.example.mediasearcher.utils.SERIES_ID_TAG
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SeriesFragment : Fragment() {

    private lateinit var layout: SeriesFragmentLayoutBinding
    private val viewModel: SeriesViewModel by viewModel()
    private lateinit var seriesAdapter: SeriesRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("SeriesFragment_TAG: onCreateView: ")
        layout = SeriesFragmentLayoutBinding.inflate(inflater, container, false)
        layout.viewModel = viewModel
        layout.lifecycleOwner = requireActivity()

        initObservers()
        initViews()
        return layout.root
    }

    private fun initObservers() {
        Timber.d("SeriesFragment_TAG: initObservers: ")
        viewModel.seriesList.observe(requireActivity()) { series ->
            if (series.isEmpty()) {
                viewModel.emptyList.postValue(true)
                return@observe
            }

            seriesAdapter.itemList = series.map {
                SeriesItemViewModel().apply {
                    serie = it
                }
            }
        }
    }

    private fun initViews() {
        Timber.d("SeriesFragment_TAG: initViews: ")
        viewModel.loading.postValue(false)

        seriesAdapter = SeriesRVAdapter { serieItem ->
            val intent = Intent(activity, SeriesDetailsActivity::class.java)
            val bundle = Bundle().apply {
                putString(SERIES_ID_TAG, serieItem.id)
            }
            intent.putExtras(bundle)
            startActivity(intent)
        }

        layout.rvSeries.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            adapter = seriesAdapter
        }

        initSearchView()
    }

    private fun initSearchView() {
        Timber.d("MoviesFragment_TAG: initSearchView: ")
        layout.svBook.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    viewModel.emptyList.postValue(true)
                    viewModel.seriesList.postValue(emptyList())
                    viewModel.totalVisible.postValue(false)
                    initViews()
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.loading.postValue(true)
                    viewModel.searchMovies(query)
                    layout.svBook.clearFocus()
                }
                return true
            }
        })
    }

}