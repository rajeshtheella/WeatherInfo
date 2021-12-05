package com.rajesh.weatherinfo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajesh.weatherinfo.base.BaseViewModel
import com.rajesh.weatherinfo.data.model.DataModel
import com.rajesh.weatherinfo.data.rest.ApiService
import kotlinx.coroutines.launch

class WeatherInfoViewModel(private val apiService: ApiService) : BaseViewModel() {
    var weatherDetails: MutableLiveData<DataModel> = MutableLiveData<DataModel>()
    fun getData() {
        viewModelScope.launch {
            loading()
            try {
                val data: DataModel = apiService.getData()
                println("data $data")
                weatherDetails.value = data
                loadingDone()
            } catch (e: Exception) {
                Log.d("DataException", e.localizedMessage)
                weatherDetails.value = DataModel()
                loadingDone()
            }
        }
    }
}