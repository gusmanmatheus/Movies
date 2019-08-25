package com.example.movies.data.local

class MOVIE_DATA {
    companion object {
        const val TABLE_NAME = "MOVIES"
        const val ID = "_id"
        const val ID_MOVIE = "ID_MOVIE"
        const val POSTER = "POSTER"
        const val TITLE = "TITLE"
        const val ORIGINAL_LANG = "ORIGINAL_LANG"
        const val ORIGINAL_TITLE = "ORIGINAL_TITLE"
        const val IMAGE = "IMAGE"
        const val ADULT = "ADULT"
        const val SYNOPSIS = "SYNOPSIS"
        const val NOTE_AVERAGE = "NUMBER_VOLTES"
        const val NUMBER_VOTES = "NUMBER_VOLTES"
        const val DATE = "DATE"
        const val GENRES = "GENRES"

        const val query = "create table $TABLE_NAME" +
                " ($ID integer primary key autoincrement," +
                " $ID_MOVIE integer not null," +
                " $POSTER text," +
                " $TITLE text," +
                " $ORIGINAL_LANG text," +
                " $ORIGINAL_TITLE text," +
                " $IMAGE text," +
                " $ADULT integer," +
                " $SYNOPSIS text," +
                " $NOTE_AVERAGE double," +
                " $NUMBER_VOTES integer," +
                " $DATE text," +
                " $GENRES text not null);"
        val COLUMNS = arrayOf(
            ID_MOVIE,
            POSTER,
            TITLE,
            ORIGINAL_LANG,
            ORIGINAL_TITLE,
            IMAGE,
            ADULT,
            SYNOPSIS,
            NOTE_AVERAGE,
            NUMBER_VOTES,
            DATE,
            GENRES

        )

    }


}