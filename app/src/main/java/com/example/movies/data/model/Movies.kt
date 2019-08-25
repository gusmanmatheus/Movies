package com.example.movies.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PageMovie(
    @SerializedName("results")
    var listMovies:MutableList<Movie>,
    var page:Int,
    @SerializedName("total_pages")
    var pageTotal:Int)

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("original_language")
    val originalLang: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("genre_ids")
    val genresId: List<Int>,
    @SerializedName("backdrop_path")
    val image: String,
    val adult: Boolean,
    @SerializedName("overview")
    val synopsis: String,
    @SerializedName("vote_count")
    val numberVotes: Int,
    @SerializedName("vote_average")
    val noteAverage: Double,
    @SerializedName("release_date")
    val date: String,
    var favorite: Boolean = false,
    var genres:String

):Serializable




