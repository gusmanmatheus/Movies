package com.example.movies.features.listMoves

import com.example.movies.data.local.MovieDAO
import com.example.movies.data.model.GenresList
import com.example.movies.data.model.PageMovie
import com.example.movies.data.remote.ServiceRequest


class MoviesPresenter(
    override var view: MoviesContract.View,
    val db: MovieDAO,
    val service: ServiceRequest
) : MoviesContract.Presenter {

    private var listGenres = GenresList(mutableListOf())
    private var pageMovie = PageMovie(mutableListOf(), 0, 0)
    var sizePage = 0
    private var current = -1
    override fun getGenres() {
        service.getGenres(
            fun(failure) {
                view.showError(failure)
            },
            fun(success) {
                listGenres = success
                nextPage()
            })
    }

    override fun loadMore() {
        val stopScroll = if (view.valueOrder()) {
            pageMovie.page >= pageMovie.pageTotal
        } else {
            pageMovie.page <= 1

        }
        if (stopScroll) {
            view.finishLoad()

        } else {
            view.revertFinishLoad()
            nextPage()

        }
    }

    override fun nextPage() {
        val numberPage = if (view.valueOrder()) {
            pageMovie.page + 1
        } else {
            current++
            pageMovie.pageTotal - current
        }

        service.getNextPage(numberPage,
            fun(failure) {
                view.showError(failure)
            },
            fun(success) {
                pageMovie = success
                orderDate()
                attValue()
                view.setupList(pageMovie.listMovies)


            })
    }

    override fun orderDate() {
        pageMovie.listMovies =
            pageMovie.listMovies.sortedWith(compareBy { it.date }).toMutableList()

    }

    private fun idToGenre() {
        var aux = ""
        //   pageMovie.listMovies.forEach {pageMovie ->
        //    pageMovie.genresId.forEach {genreId ->
        //     if (listGenres.listGenres.any { it.id == genreId })

        //  }
        // }
    }

    private fun attValue() {
        this.sizePage = if (pageMovie.listMovies.size > 0) (pageMovie.listMovies.size / 2) else 0
    }

    override fun getFavorites() {

    }

    override fun resetOrder() {
        pageMovie.pageTotal = 1
        pageMovie.page = 0
    }

    override fun resetReverseOrder() {
        current = -1
        pageMovie.page = pageMovie.pageTotal
    }

}