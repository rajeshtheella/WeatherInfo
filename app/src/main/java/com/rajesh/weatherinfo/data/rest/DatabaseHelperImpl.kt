package com.rajesh.weatherinfo.data.rest

import com.rajesh.weatherinfo.data.model.UserModel


class DatabaseHelperImpl(private val appDataBase: AppDataBase) : DatabaseHelper {
    override suspend fun getAll(): List<UserModel> = appDataBase.userDao().getAll()

    override suspend fun delete(data: UserModel) = appDataBase.userDao().delete(data)

    override suspend fun insert(data: UserModel) = appDataBase.userDao().insert(data)
    override fun insertData(data: UserModel) = appDataBase.userDao().insertData(data)
    override fun deleteUser(data: UserModel) = appDataBase.userDao().deleteUser(data)

}