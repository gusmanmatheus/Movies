package com.example.movies.features.listMoves

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MoviesListActivity : AppCompatActivity(), MoviesContract.View {
    override val presenter: MoviesPresenter by inject { parametersOf(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
