package com.rajesh.weatherinfo.base

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel():ViewModel() {
    var error= MutableLiveData<Exception>()
    var loading= MutableLiveData<Int>()
    fun loading() {
        if (loading.value != null) {
            val value = loading.value ?: 0
            loading.value = value + 1
        } else {
            loading.value = 1
        }
    }

    fun loadingDone() {
        if (loading.value != null) {
            val value = loading.value ?: 0
            loading.value = value - 1
        } else {
            loading.value = 0
        }
    }

    fun checkEmpty(strPassword: String): Boolean {
        if (strPassword.isEmpty()) {
            return false
        }
        return true
    }

    fun checkEmail(strEmail: String): Boolean {
        if (strEmail.isEmpty()) {
            return false
        } else if (Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            return true
        }
        return false
    }
}