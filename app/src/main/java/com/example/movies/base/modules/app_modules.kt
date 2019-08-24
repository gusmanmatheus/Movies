package com.example.movies.base.modules

import com.example.movies.data.local.MovieDAO
import com.example.movies.data.remote.ServiceRequest
import com.example.movies.features.listMoves.MoviesContract
import com.example.movies.features.listMoves.MoviesPresenter
import org.koin.dsl.bind
import org.koin.dsl.module

val baseModule = module {
    factory { ServiceRequest() }
    factory { MovieDAO(get()) }
}
val listModule = module {
    factory { (view: MoviesContract.View) ->
        MoviesPresenter(
            view = view,
            db = get(),
            service = get()
        )
    }bind MoviesPresenter::class
}