package com.example.movies.features.listMoves

import com.example.movies.base.BasePresenter
import com.example.movies.base.BaseView
import com.example.movies.data.model.Movie

interface MoviesContract {
    interface View : BaseView<Presenter> {
        fun setupList(listMovies: MutableList<Movie>)
        fun finishLoad()
        fun revertFInishLoad()
        fun showError(error: String)
        fun valueOrder(): Boolean

    }

    interface Presenter : BasePresenter<View> {
        fun getGenres()
        fun loadMore()
        fun nextPage(page: Int)
        fun orderDate(list: MutableList<Movie>)
        fun getFavorites()
        fun resetOrder()
    }
}