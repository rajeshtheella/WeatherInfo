package com.rajesh.weatherinfo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajesh.weatherinfo.base.BaseViewModel
import com.rajesh.weatherinfo.data.model.DataModel
import com.rajesh.weatherinfo.data.model.Weather
import com.rajesh.weatherinfo.data.rest.ApiService
import com.rajesh.weatherinfo.supportFiles.Constants
import com.rajesh.weatherinfo.supportFiles.CustomSharedPreferences
import kotlinx.coroutines.launch

class LoginViewModel(private val apiService: ApiService) : BaseViewModel() {
    var weatherDetails: MutableLiveData<DataModel> = MutableLiveData<DataModel>()
    var mLoginStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    fun getData() {
        viewModelScope.launch {
            loading()
            try {
                val data: DataModel = apiService.getData()
                println("data $data")
                weatherDetails.value = data
            } catch (e: Exception) {
                Log.d("DataException", e.localizedMessage)
                weatherDetails.value = DataModel()
                loadingDone()
            }
        }
    }

    fun saveUser(
        email: String,
        password: String,
        mCustomSharedPreferences: CustomSharedPreferences
    ) {
        viewModelScope.launch {
            loading()
            try {

                mCustomSharedPreferences.addValue(Constants.IS_LOGGED,true)
                mCustomSharedPreferences.addValue(Constants.USER_STATE,true)
                mCustomSharedPreferences.addValue(Constants.USER_EMAIL,email)
                mCustomSharedPreferences.addValue(Constants.USER_PASSWORD,password)
                mLoginStatus.value = true
            } catch (e: java.lang.Exception) {
                mLoginStatus.value = false
                Log.d("user exception", e.localizedMessage)
            }
        }

    }
}