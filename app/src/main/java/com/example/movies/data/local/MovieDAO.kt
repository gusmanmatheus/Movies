package com.example.movies.data.local

import android.content.ContentValues
import android.content.Context
import com.example.movies.base.BaseDAO
import com.example.movies.data.model.Movie

class MovieDAO(context: Context) : BaseDAO(context) {
    override val query: String
        get() = MOVIE_DATA.query

    fun insert(movie: Movie): Boolean {
        val db = writableDatabase
        var result = true
        try {
            db.insert(MOVIE_DATA.TABLE_NAME, null, objectToContentValues(movie))
        } catch (e: Exception) {
            result = false
        } finally {
            db.close()
        }
        return result
    }

    fun getMovies(): List<Movie> {

        val db = writableDatabase
        val list = mutableListOf<Movie>()

        val cursor =
            db.query(
                MOVIE_DATA.TABLE_NAME,
                MOVIE_DATA.COLUMNS,
                null,
                null,
                null,
                null,
                MOVIE_DATA.DATE
            )
        if (cursor != null && cursor.moveToFirst()) {
            do {
                list.add(
                    Movie(
                        cursor.getInt(0),
                        cursor.getString(1) ?: " ",
                        cursor.getString(2) ?: " ",
                        cursor.getString(3) ?: " ",
                        cursor.getString(4) ?: " ",
                        emptyList(),
                        cursor.getString(5) ?: " ",
                        cursor.getInt(6) > 0,
                        cursor.getString(7) ?: " ",
                        cursor.getInt(8) ?: 0,
                        cursor.getDouble(9) ?: 0.0,
                        cursor.getString(10) ?: " ",
                        true,
                        cursor.getString(11) ?: " "
                    )
                )
            } while (cursor.moveToNext())
        }
        db.close()
        return list
    }

    fun getMovie(idMovie: Int): List<Int> {
        val db = writableDatabase
        val list = mutableListOf<Int>()
        val cursor = db.query(
            MOVIE_DATA.TABLE_NAME,
            arrayOf(MOVIE_DATA.ID_MOVIE),
            "${MOVIE_DATA.ID_MOVIE} =?",
            arrayOf("$idMovie"),
            null,
            null,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            do {
                list.add(cursor.getInt(0))

            } while (cursor.moveToNext())
        }
        db.close()
        return list
    }

    fun deleteMovie(idMovie: Int) {
        val db = writableDatabase
        db.delete(
            MOVIE_DATA.TABLE_NAME,
            "${MOVIE_DATA.ID_MOVIE} = $idMovie",
            null
        )
        db.close()

    }

    fun objectToContentValues(movie: Movie): ContentValues {
        val values = ContentValues()
        values.put(MOVIE_DATA.ID_MOVIE, movie.id)
        values.put(MOVIE_DATA.POSTER, movie.poster)
        values.put(MOVIE_DATA.TITLE, movie.title)
        values.put(MOVIE_DATA.ORIGINAL_LANG, movie.originalLang)
        values.put(MOVIE_DATA.ORIGINAL_TITLE, movie.originalTitle)
        values.put(MOVIE_DATA.IMAGE, movie.image)
        values.put(MOVIE_DATA.ADULT, movie.adult)
        values.put(MOVIE_DATA.SYNOPSIS, movie.synopsis)
        values.put(MOVIE_DATA.NUMBER_VOTES, movie.numberVotes)
        values.put(MOVIE_DATA.NOTE_AVERAGE, movie.noteAverage)
        values.put(MOVIE_DATA.DATE, movie.date)
        values.put(MOVIE_DATA.GENRES, movie.genres)

        return values
    }
}