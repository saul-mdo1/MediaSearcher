package com.example.mediasearcher.presentation.details.series

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mediasearcher.R
import com.example.mediasearcher.databinding.MovieDetailsActivityLayoutBinding
import com.example.mediasearcher.databinding.SeriesDetailsActivityLayoutBinding
import com.example.mediasearcher.utils.MOVIE_ID_TAG
import com.example.mediasearcher.utils.SERIES_ID_TAG
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SeriesDetailsActivity : AppCompatActivity() {
    private lateinit var layout: SeriesDetailsActivityLayoutBinding
    private val viewModel: SeriesDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("MovieDetailsActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        layout = DataBindingUtil.setContentView(this, R.layout.series_details_activity_layout)
        layout.viewModel = viewModel
        layout.lifecycleOwner = this

        initObservers()
        getMovieId()
    }


    private fun getMovieId() {
        Timber.d("MovieDetailsActivity_TAG: getMovieId: ")
        viewModel.loading.postValue(true)
        val id = intent.extras?.getString(SERIES_ID_TAG)
        id?.let {
            viewModel.getSeriesById(it)
        }
    }

    private fun initObservers() {
        Timber.d("MovieDetailsActivity_TAG: initObservers: ")
        viewModel.serie.observe(this) { movie ->
            movie?.let { m ->
                m.ratings?.forEach { rating ->
                    val textView = TextView(this)
                    textView.text = "${rating.source}: ${rating.value}"
                    layout.layoutProductionDetails.addView(textView)
                }
            }
        }
    }
}