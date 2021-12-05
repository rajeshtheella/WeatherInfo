package com.rajesh.weatherinfo.data.rest

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rajesh.weatherinfo.data.model.UserModel

@Database(entities = [UserModel::class],version = 1,exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    private val instance:AppDataBase? = null
    abstract fun userDao():UserDao
}