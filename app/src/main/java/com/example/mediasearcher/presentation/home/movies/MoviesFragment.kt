package com.example.mediasearcher.presentation.home.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediasearcher.databinding.MoviesFragmentLayoutBinding
import com.example.mediasearcher.presentation.details.movies.MovieDetailsActivity
import com.example.mediasearcher.utils.MOVIE_ID_TAG
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MoviesFragment : Fragment() {

    private lateinit var layout: MoviesFragmentLayoutBinding
    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("MoviesFragment_TAG: onCreateView: ")
        layout = MoviesFragmentLayoutBinding.inflate(inflater, container, false)
        layout.viewModel = viewModel
        layout.lifecycleOwner = requireActivity()

        initObservers()
        initViews()
        return layout.root
    }

    private fun initObservers() {
        Timber.d("MoviesFragment_TAG: initObservers: ")
        viewModel.moviesList.observe(requireActivity()) { movies ->
            if (movies.isEmpty()) {
                viewModel.emptyList.postValue(true)
                return@observe
            }

            moviesAdapter.itemList = movies.map {
                MovieItemViewModel().apply {
                    movie = it
                }
            }
        }
    }

    private fun initViews() {
        Timber.d("MoviesFragment_TAG: initViews: ")
        viewModel.loading.postValue(false)

        moviesAdapter = MoviesRVAdapter { movieItem ->
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            val bundle = Bundle().apply {
                putString(MOVIE_ID_TAG, movieItem.id)
            }
            intent.putExtras(bundle)
            startActivity(intent)
        }

        layout.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            adapter = moviesAdapter
        }

        initSearchView()
    }

    private fun initSearchView() {
        Timber.d("MoviesFragment_TAG: initSearchView: ")
        layout.svBook.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    viewModel.emptyList.postValue(true)
                    viewModel.moviesList.postValue(emptyList())
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