package com.example.movies.base

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}