package com.example.movies.features.listMoves

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.movies.BuildConfig.base_image
import com.example.movies.R
import com.example.movies.data.model.Movie
import kotlinx.android.synthetic.main.movie_layout.view.*


class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.Holder>() {

    private var isFinished = false
    var data: MutableList<Movie> = mutableListOf()
    var onItemClick: ((Movie) -> Unit) = { }

    companion object {
        const val HOLDER_MOVIE_TYPE = 1
        const val HOLDER_LOADING_TYPE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            HOLDER_MOVIE_TYPE -> MovieHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.movie_layout,
                    parent,
                    false
                )
            )
            else -> LoadingHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.movie_loading,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = data.size + if (isFinished) 1 else 0

    fun isFinished() {
        this.isFinished = true
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        data.getOrNull(position)?.let {
            holder.render(it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position < data.size -> HOLDER_MOVIE_TYPE
            else -> HOLDER_LOADING_TYPE
        }
    }

    fun setData(data: List<Movie>, isFinished: Boolean = false) {
        this.data = data.toMutableList()
        this.isFinished = isFinished
        notifyDataSetChanged()
    }

    fun addData(data: List<Movie>, isFinished: Boolean = false) {
        this.isFinished = isFinished
        val positionStart = data.size
        this.data.addAll(data)
        val positionEnd = data.size + if (isFinished) 1 else 0
        notifyItemRangeChanged(positionStart, positionEnd)
    }

    abstract inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun render(movie: Movie) {}
    }

    inner class MovieHolder(itemView: View) : Holder(itemView) {
        init {
            itemView.setOnClickListener {
                data.getOrNull(adapterPosition)?.let { movie ->
                    onItemClick.invoke(movie)

                }
            }
        }

        override fun render(movie: Movie) {
            itemView.titleMovie.text = movie.title
            itemView.dateMovie.text = datePatterBr(movie.date)
            itemView.genres.text = movie.genres
            itemView.adult.text =
                if (movie.adult) itemView.context.resources.getString(R.string.preAdult) else ""
            itemView.note.text =
                itemView.context.resources.getString(R.string.preNote) + "${movie.noteAverage}"
            itemView.language.text =
                itemView.context.resources.getString(R.string.preLangOri) + "${movie.originalLang.toUpperCase()}"

            Glide.with(itemView.context)
                .load(base_image + movie.poster)
                .into(itemView.imageMovie)
        }
    }

    fun datePatterBr(date: String): String {
        val dateBr = date.split("-")
        return "${dateBr[2]}/${dateBr[1]}/${dateBr[0]}"
    }

    inner class LoadingHolder(itemView: View) : Holder(itemView)
}

