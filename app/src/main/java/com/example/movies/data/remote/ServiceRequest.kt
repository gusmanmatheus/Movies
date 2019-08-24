package com.example.movies.data.remote

import com.example.movies.BuildConfig.api_key
import com.example.movies.BuildConfig.language
import com.example.movies.data.model.GenresList
import com.example.movies.data.model.PageMovie
import com.example.movies.data.remote.ServiceProvider.Companion.request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRequest {

    fun getNextPage(
        page: Int,
        failure: (failure: String) -> Unit,
        success: (pageMovie: PageMovie) -> Unit
    ) {
        request.getPageMovies(api_key, language, page)
            .enqueue(object : Callback<PageMovie> {
                override fun onFailure(call: Call<PageMovie>, t: Throwable) {
                    failure(t.message.toString())
                }

                override fun onResponse(call: Call<PageMovie>, response: Response<PageMovie>) {
                    response.body()?.let(success)
                }

            })
    }

    fun getGenres(
        failure: (failure: String) -> Unit,
        success: (listGenres: GenresList) -> Unit
    ) {
        request.getGenres(api_key,language)
            .enqueue(object : Callback<GenresList> {
                override fun onFailure(call: Call<GenresList>, t: Throwable) {
                    failure(t.message.toString())
                }

                override fun onResponse(call: Call<GenresList>, response: Response<GenresList>) {
                    response.body()?.let(success)
                }

            })
    }
}