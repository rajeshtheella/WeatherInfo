package com.rajesh.weatherinfo.di.modules

import com.rajesh.weatherinfo.ui.fragments.OnBoardViewModel
import com.rajesh.weatherinfo.ui.viewmodel.UserListViewModel
import com.rajesh.weatherinfo.ui.viewmodel.LoginViewModel
import com.rajesh.weatherinfo.ui.viewmodel.WeatherInfoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ LoginViewModel(get()) }
    viewModel { UserListViewModel() }
    viewModel { WeatherInfoViewModel(get()) }
    viewModel { OnBoardViewModel() }

}
