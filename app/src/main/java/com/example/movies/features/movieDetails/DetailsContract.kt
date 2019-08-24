package com.example.movies.features.movieDetails

import com.example.movies.base.BasePresenter
import com.example.movies.base.BaseView

interface DetailsContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter<View> {

    }
}
