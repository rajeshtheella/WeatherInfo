package com.rajesh.weatherinfo.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rajesh.weatherinfo.base.BaseViewModel
import com.rajesh.weatherinfo.data.model.UserModel
import com.rajesh.weatherinfo.data.rest.DatabaseBuilder
import com.rajesh.weatherinfo.data.rest.DatabaseHelperImpl
import kotlinx.coroutines.launch
import java.lang.Exception

class UserListViewModel() : BaseViewModel() {

    var mInsertStatus = MutableLiveData<Boolean>()
    var mRemoveStatus = MutableLiveData<Boolean>()
    var mUserData = MutableLiveData<ArrayList<UserModel>>()
    fun saveUser(data: UserModel, context: Context) {
        val helper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context = context))
        viewModelScope.launch {
            loading()
            try {
                Thread {
                    helper.insertData(data)
                   // println(helper.insertData(data))
                }.start()

                mInsertStatus.value = true
                loadingDone()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage)
                mInsertStatus.value = false
                loadingDone()
            }
        }
    }
    fun getData(context: Context){
        val helper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context = context))
        viewModelScope.launch {
            loading()
            try {
                val data:ArrayList<UserModel> = helper.getAll() as ArrayList<UserModel>
                mUserData.value = data
                    println(" get data   ${helper.getAll()}")
                loadingDone()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage)
                mUserData.value = ArrayList<UserModel>()
                loadingDone()
            }
        }
    }

    fun removeUser(userModel: UserModel,context: Context) {
        Log.d("removed","removed")
        val helper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context = context))
        viewModelScope.launch {
            loading()
            try {
                Thread {
                    helper.deleteUser(userModel)
                    println(helper.deleteUser(userModel))
                }.start()

                mRemoveStatus.value = true
                loadingDone()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage)
                mRemoveStatus.value = false
                loadingDone()
            }
        }
    }
}