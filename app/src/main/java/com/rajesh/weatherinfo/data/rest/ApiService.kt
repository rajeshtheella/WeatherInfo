package com.rajesh.weatherinfo.data.rest

import com.rajesh.weatherinfo.data.model.DataModel
import retrofit2.http.GET

interface ApiService {
    //products
    //onecall?lat=12.9082847623315&lon=77.65197822993314&units=imperial&appid=b143bb707b2ee117e62649b358207d3e
    @GET("onecall?lat=12.9082847623315&lon=77.65197822993314&units=imperial&appid=b143bb707b2ee117e62649b358207d3e")
    suspend fun getData(): DataModel
}