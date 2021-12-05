package com.rajesh.weatherinfo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "firstName") val firstName: String? = null,
    @ColumnInfo(name = "LastName") val LastName: String? = null,
    @ColumnInfo(name = "Email")  val Email: String? = null
)
