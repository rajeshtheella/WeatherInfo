package com.rajesh.weatherinfo.data.rest

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var INSTANCE: AppDataBase?= null

    fun getInstance(context: Context):AppDataBase{
        if(INSTANCE == null){
            synchronized(AppDataBase::class){
                INSTANCE = buildRoomDB(context)
            }
        }
        return  INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
         Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"users")
             .fallbackToDestructiveMigration()
             .build()
}