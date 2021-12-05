package com.rajesh.weatherinfo.di.modules

import com.rajesh.weatherinfo.supportFiles.CustomSharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreferenceModule= module {
    single {  CustomSharedPreferences(androidContext()) }
}