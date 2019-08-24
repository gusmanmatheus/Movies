package com.example.movies.features.listMoves

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.data.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MoviesListActivity : AppCompatActivity(), MoviesContract.View {

    override val presenter: MoviesPresenter by inject { parametersOf(this) }
    private val adapter by lazy {
        MoviesAdapter()
    }
    private lateinit var llm:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llm = LinearLayoutManager(this)
        setupRecyclerView()
        presenter.getGenres()

    }
    private fun setupRecyclerView(){
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
    }

    override fun setupList(listMovies: MutableList<Movie>) {
        if (adapter.data.size > 0) {
            adapter.addData(listMovies)
        } else {
            adapter.setData(listMovies)
        }
    }


    override fun finishLoad() {
    }

    override fun revertFInishLoad() {
    }

    override fun showError(error: String) {
    }

    override fun valueOrder(): Boolean {
        return true
    }

}
