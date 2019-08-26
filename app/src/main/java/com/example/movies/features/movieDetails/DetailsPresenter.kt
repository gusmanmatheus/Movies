package com.example.movies.features.movieDetails

import com.example.movies.data.local.MovieDAO
import com.example.movies.data.model.Movie

class DetailsPresenter(
    override var view: DetailsContract.View,
    private val db: MovieDAO
) : DetailsContract.Presenter {
    private lateinit var movie: Movie

    override fun getMovie() = this.movie
    override fun setMovie(movie: Movie) {
        this.movie = movie
    }

    override fun veryMovie(id: Int): Boolean {
        return db.getMovie(id).isNotEmpty()

    }

    override fun deleteMovie(id: Int) {
        db.deleteMovie(id)
    }

    override fun insertMovie(movie: Movie) {
        db.insert(movie)
    }

    override fun insertOrDelete(id: Int) {
        if (veryMovie(id)) deleteMovie(id) else insertMovie(this.movie)
    }

    override fun changeStatusFavorite() {
        this.movie.favorite = !this.movie.favorite
    }
}