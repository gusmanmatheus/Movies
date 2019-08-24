package com.example.movies.features.listMoves

import com.example.movies.data.local.MovieDAO
import com.example.movies.data.model.GenresList
import com.example.movies.data.model.Movie
import com.example.movies.data.model.PageMovie
import com.example.movies.data.remote.ServiceRequest

class MoviesPresenter(
    override var view: MoviesContract.View,
    val db: MovieDAO,
    val service: ServiceRequest
) : MoviesContract.Presenter {


    private var listGenres = GenresList(mutableListOf())
    private var pageMovie = PageMovie(mutableListOf(), 0, 0)
    override fun getGenres() {
        service.getGenres(
            fun(failure) {
                view.showError(failure)
            },
            fun(success) {
                listGenres = success
                nextPage(1)
            })
    }

    override fun loadMore() {
    }

    override fun nextPage(page: Int) {
        service.getNextPage(page,
            fun(failure) {
                view.showError(failure)
            }, fun(success) {
                pageMovie = success
                view.setupList(pageMovie.listMovies)
            })
    }

    override fun orderDate(list: MutableList<Movie>) {
    }

    override fun getFavorites() {
    }

    override fun resetOrder() {
    }


}