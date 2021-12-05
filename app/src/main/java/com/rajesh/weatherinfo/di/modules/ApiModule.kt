package com.rajesh.weatherinfo.di.modules

import com.rajesh.weatherinfo.data.rest.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = true) { get<Retrofit>().create(ApiService::class.java) }
   // single { get<Retrofit>().create(ApiService::class.java) }

}