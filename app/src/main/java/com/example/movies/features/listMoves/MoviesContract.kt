package com.example.movies.features.listMoves

import com.example.movies.base.BasePresenter
import com.example.movies.base.BaseView

interface MoviesContract {
    interface View : BaseView<Presenter> {


    }

    interface Presenter : BasePresenter<View> {

    }
}