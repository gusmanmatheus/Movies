package com.example.movies.base.modules

import com.example.movies.data.remote.ServiceRequest
import org.koin.dsl.module

val moduleBase = module {
    factory {
        ServiceRequest()
    }
}