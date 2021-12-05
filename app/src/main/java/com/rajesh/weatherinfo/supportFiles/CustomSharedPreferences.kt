package com.rajesh.weatherinfo.supportFiles

import android.content.Context
import android.content.SharedPreferences

class CustomSharedPreferences(private val mContext: Context) {
    private val mSharedPreferences:SharedPreferences by lazy { mContext.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE) }
    private val mEditor:SharedPreferences.Editor by lazy { mSharedPreferences.edit() }
    fun addValue(strKey:String,value:Any){
        when(value){
            is Int ->mEditor.putInt(strKey,value)
            is String ->mEditor.putString(strKey,value)
            is Long ->mEditor.putLong(strKey,value)
            is Float ->mEditor.putFloat(strKey,value)
            is Boolean ->mEditor.putBoolean(strKey,value)
        }
        mEditor.commit()
    }

    fun getValue(strKey: String,type:PrefDataType):Any{
        return when(type){
            PrefDataType.BOOLEAN -> mSharedPreferences.getBoolean(strKey,false)
            PrefDataType.FLOAT ->mSharedPreferences.getFloat(strKey,0.0f)
            PrefDataType.LONG ->mSharedPreferences.getLong(strKey,0L)
            PrefDataType.STRING ->mSharedPreferences.getString(strKey,"")!!
            PrefDataType.INT ->mSharedPreferences.getInt(strKey,0)

        }
    }

    fun clearData(){
        mSharedPreferences.edit().clear().apply()
    }

}