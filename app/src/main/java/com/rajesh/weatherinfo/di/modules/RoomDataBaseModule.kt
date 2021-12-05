package com.rajesh.weatherinfo.di.modules

import androidx.room.Room
import com.rajesh.weatherinfo.data.rest.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDataBaseModule = module {
    factory { Room.databaseBuilder(androidContext(), AppDataBase::class.java,"users").build() }

}