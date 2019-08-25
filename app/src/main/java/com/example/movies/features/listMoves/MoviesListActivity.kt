package com.example.movies.features.listMoves

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
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
    private lateinit var llm: LinearLayoutManager
    private var loadListLocked = false
    private var onePage = false
    private var orderList = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llm = LinearLayoutManager(this)
        setupRecyclerView()
        presenter.getGenres()
        scrollLoading()
        reverse.setOnClickListener { invertAction() }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
    }

    private fun scrollLoading() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                adapter.notifyItemRemoved(adapter.itemCount)
                if (loadListLocked) return

                val lastVisibleMoviePosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if ((lastVisibleMoviePosition + presenter.sizePage) > adapter.itemCount && !onePage) {
                    onePage = true
                    presenter.loadMore()
                }
            }
        })
    }

    private fun invertAction(): Boolean {
        this.orderList = !orderList
        adapter.setData(emptyList())
        if (orderList) {
            presenter.resetOrder()
        } else {
            presenter.resetReverseOrder()
        }
        presenter.loadMore()

        return orderList
    }

    override fun setupList(listMovies: MutableList<Movie>) {
        if (adapter.data.size > 0) {
            adapter.addData(listMovies)
        } else {
            adapter.setData(listMovies)
        }
        onePage = false
    }


    override fun finishLoad() {
        this.loadListLocked = true
    }

    override fun revertFinishLoad() {
        this.loadListLocked = false
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }


    override fun valueOrder(): Boolean {
        return this.orderList
    }
}
