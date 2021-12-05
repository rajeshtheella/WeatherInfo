package com.rajesh.weatherinfo.data.model

data class DataModel (
    val lat: Double? = null,
    val lon: Double? = null,
    val timezone: String? = null,
    val timezone_offset: Long? = null,
    val current: Current? = null
)

data class Current (
    val dt: Long? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: Double? = null,
    val feels_like: Double? = null,
    val pressure: Long? = null,
    val humidity: Long? = null,
    val dew_point: Double? = null,
    val uvi: Double? = null,
    val clouds: Long? = null,
    val visibility: Long? = null,
    val wind_speed: Double? = null,
    val windDeg: Long? = null,
    val weather: List<Weather>? = null
)

data class Weather (
    val id: Long? = null,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)
