package com.example.movies.data.remote

import com.example.movies.BuildConfig.base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceProvider {
    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request: ApiService = retrofit.create(ApiService::class.java)
    }
}