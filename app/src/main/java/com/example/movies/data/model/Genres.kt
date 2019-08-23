package com.example.movies.data.model


data class GenresList(val listGenres: MutableList<Genres>)

data class Genres(
    val id: Int,
    val name: String
)