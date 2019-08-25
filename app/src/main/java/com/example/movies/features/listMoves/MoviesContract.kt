package com.example.movies.features.listMoves

import com.example.movies.base.BasePresenter
import com.example.movies.base.BaseView
import com.example.movies.data.model.Movie

interface MoviesContract {
    interface View : BaseView<Presenter> {
        fun setupList(listMovies: MutableList<Movie>)
        fun finishLoad()
        fun revertFinishLoad()
        fun showError(error: String)
        fun valueOrder(): Boolean

    }

    interface Presenter : BasePresenter<View> {
        fun getGenres()
        fun loadMore()
        fun nextPage()
        fun orderDate()
        fun getFavorites(): List<Movie>
        fun resetOrder()
        fun resetReverseOrder()
        fun setBackupList(movies: MutableList<Movie>)
        fun recoveryBackup(): MutableList<Movie>
        fun invertList(mutableList: MutableList<Movie>): MutableList<Movie>
    }
}