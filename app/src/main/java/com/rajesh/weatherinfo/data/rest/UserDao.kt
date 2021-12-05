package com.rajesh.weatherinfo.data.rest

import androidx.room.*
import com.rajesh.weatherinfo.data.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserModel ORDER BY id DESC")
    suspend fun getAll():List<UserModel>

    @Delete
    suspend fun  delete(data:UserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: UserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(data: UserModel)

    @Delete
     fun  deleteUser(data:UserModel)
}