package com.example.movies.features.listMoves

import com.example.movies.data.local.MovieDAO
import com.example.movies.data.remote.ServiceRequest

class MoviesPresenter(
    override var view: MoviesContract.View,
    val db: MovieDAO,
    val service:ServiceRequest
) : MoviesContract.Presenter {


}