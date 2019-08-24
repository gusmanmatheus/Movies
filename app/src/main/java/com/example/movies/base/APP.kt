package com.example.movies.base

import android.app.Application
import com.example.movies.base.modules.baseModule
import com.example.movies.base.modules.listModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class APP : Application() {
    private val modules = listOf(
        baseModule,
        listModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@APP)
            modules(modules)
        }
    }
}