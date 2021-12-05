package com.rajesh.weatherinfo.base

import android.app.Application
import com.rajesh.weatherinfo.di.modules.networkModule
import com.rajesh.weatherinfo.di.modules.roomDataBaseModule
import com.rajesh.weatherinfo.di.modules.sharedPreferenceModule
import com.rajesh.weatherinfo.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(listOf(networkModule,viewModelModule, sharedPreferenceModule))
        }
    }
    fun getInstance():BaseApp{
        return BaseApp()
    }
}