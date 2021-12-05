package com.rajesh.weatherinfo.data.rest

import androidx.room.Delete
import androidx.room.Insert
import com.rajesh.weatherinfo.data.model.UserModel


interface DatabaseHelper {
    suspend fun getAll(): List<UserModel>

    suspend fun delete(data: UserModel)
    fun deleteUser(data: UserModel)

    suspend fun insert(data: UserModel)
    fun insertData(data: UserModel)

}