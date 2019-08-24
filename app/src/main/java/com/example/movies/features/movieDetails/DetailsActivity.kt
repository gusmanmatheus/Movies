package com.example.movies.features.movieDetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsActivity : AppCompatActivity(),DetailsContract.View {
    override val presenter:DetailsPresenter  by inject { parametersOf(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}
