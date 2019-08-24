package com.example.movies.base

import android.app.Application
import com.example.movies.base.modules.moduleBase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class APP : Application() {
    private val modules = listOf(
        moduleBase
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@APP)
            modules(modules)
        }
    }
}