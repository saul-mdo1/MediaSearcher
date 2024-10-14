package com.example.mediasearcher.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mediasearcher.R
import com.example.mediasearcher.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var layout: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("MainActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)

        layout = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layout.lifecycleOwner = this

        val navController = findNavController(R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_books,
                R.id.navigation_movies,
                R.id.navigation_series
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        layout.navView.setupWithNavController(navController)
    }
}

//EXAMPLE OF ROUTE: https://www.googleapis.com/books/v1/volumes?q=stephen+king&langRestrict=es