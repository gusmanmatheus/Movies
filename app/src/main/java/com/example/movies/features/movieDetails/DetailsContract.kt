package com.example.movies.features.movieDetails

import com.example.movies.base.BasePresenter
import com.example.movies.base.BaseView
import com.example.movies.data.model.Movie

interface DetailsContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter<View> {
        fun setMovie(movie: Movie)
        fun veryMovie(id: Int): Boolean
        fun deleteMovie(id: Int)
        fun insertMovie(movie: Movie)
        fun getMovie(): Movie
        fun insertOrDelete(id: Int)
        fun changeStatusFavorite()
    }
}
