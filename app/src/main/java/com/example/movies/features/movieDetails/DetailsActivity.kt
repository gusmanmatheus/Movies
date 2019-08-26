package com.example.movies.features.movieDetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.movies.BuildConfig
import com.example.movies.R
import com.example.movies.data.model.Movie
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsActivity : AppCompatActivity(), DetailsContract.View {
    override val presenter: DetailsPresenter  by inject { parametersOf(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        recoveryData()
        setupToolbar()
        favorite()
    }


    private fun recoveryData() {
        val movie =
            intent.getSerializableExtra(resources.getString(R.string.intentMovieToDetails)) as Movie
        presenter.setMovie(movie)
        titleDetail.text = resources.getString(R.string.preTitle) + movie.title
        oriTitleDetail.text = resources.getString(R.string.preTitleOri)+ movie.originalTitle
        oriLanguageDetail.text = resources.getString(R.string.preLangOri)+ movie.originalLang
        genresDetail.text = resources.getString(R.string.preGenre) + movie.genres
        adultDetail.text = if (movie.adult) resources.getString(R.string.preAdult) else " "
        noteDetail.text = resources.getString(R.string.preNote)+"${movie.noteAverage}"
        numVotesDetail.text = resources.getString(R.string.preNumVotes)+"${movie.numberVotes}"
        synopsisDetail.text = resources.getString(R.string.preSynopsis) + movie.synopsis
        if (presenter.veryMovie(movie.id)) {
            favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp)
            movie.favorite = true
        }

        Glide.with(backdrop.context)
            .load(BuildConfig.base_image + movie.poster)
            .into(backdrop)
    }

    private fun favorite() {
        favoriteButton.setOnClickListener {
            presenter.insertOrDelete(presenter.getMovie().id)
            presenter.changeStatusFavorite()
            if (presenter.getMovie().favorite) {
                favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp)
            } else {
                favoriteButton.setImageResource(R.drawable.ic_favorite_border_red_24dp)

            }

        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home
            -> {
                onBackPressed()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
