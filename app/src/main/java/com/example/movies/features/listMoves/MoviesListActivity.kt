package com.example.movies.features.listMoves

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.movies.R
import com.example.movies.data.model.Movie
import com.example.movies.features.movieDetails.DetailsActivity
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
    private var listFocus = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llm = LinearLayoutManager(this)
        setupRecyclerView()
        presenter.getGenres()
        scrollLoading()
        inversor()
        movieClick()
        selectedMenuItem()
    }

    override fun onStart() {
        super.onStart()
        if (listFocus) return
        adapter.setData(presenter.getFavorites())
        adapter.notifyDataSetChanged()
        adapter.notifyItemRemoved(adapter.itemCount)

    }

    fun inversor() {
        reverse.setOnClickListener {
            if (!listFocus) {
                adapter.data = presenter.invertList(adapter.data)
                adapter.notifyDataSetChanged()
            } else {
                invertAction()
            }
        }
    }

    private fun selectedMenuItem() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favorite -> {
                    favoritesChanged()
                }
                R.id.nav_list -> {
                    listChanged()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun favoritesChanged() {
        if (!listFocus) return
        listFocus = !listFocus
        presenter.setBackupList(adapter.data)
        adapter.setData(presenter.getFavorites())
        adapter.notifyDataSetChanged()
        //adapter.notifyItemChanged(adapter.itemCount)


    }

    private fun listChanged() {
        if (listFocus) return
        listFocus = !listFocus
        adapter.setData(emptyList())
        adapter.setData(presenter.recoveryBackup())
        adapter.notifyDataSetChanged()

    }

    private fun movieClick() {
        adapter.onItemClick = {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(resources.getString(R.string.intentMovieToDetails), it)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
    }

    private fun scrollLoading() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (loadListLocked || !listFocus) {
                    adapter.notifyItemRemoved(adapter.itemCount)
                    return
                }
                val lastVisibleMoviePosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if ((lastVisibleMoviePosition + presenter.sizePage) >= adapter.itemCount && !onePage) {
                    onePage = true
                    presenter.loadMore()
                }
            }
        })
    }

    private fun invertAction(): Boolean {
        this.orderList = !orderList
        adapter.setData(emptyList())
        adapter.notifyDataSetChanged()
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

        if (adapter.data.size <= 4) {
            presenter.loadMore()
        }
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
