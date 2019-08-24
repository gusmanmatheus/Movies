package com.example.movies.data.model

import com.google.gson.annotations.SerializedName


data class GenresList(
    @SerializedName("genres")
    val listGenres: MutableList<Genres>
)

data class Genres(
    val id: Int,
    val name: String
)