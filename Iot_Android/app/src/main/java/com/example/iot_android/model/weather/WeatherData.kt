package com.example.iot_android.model.weather

data class WeatherData(
    val lat: Int,
    val lon: Int,
    val timezone: String,
    val timezone_offset: Int,
    val daily: ArrayList<Daily>
)