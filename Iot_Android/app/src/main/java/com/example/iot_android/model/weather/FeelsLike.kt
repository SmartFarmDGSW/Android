package com.example.iot_android.model.weather

data class FeelsLike(
    val day: Float,
    val night: Float,
    val eve: Float,
    var morn: Float
)