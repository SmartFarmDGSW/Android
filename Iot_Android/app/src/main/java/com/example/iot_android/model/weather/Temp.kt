package com.example.iot_android.model.weather

data class Temp(
    val day: Float,
    val min: Float,
    val max: Float,
    val night: Float,
    val eve: Float,
    val morn: Float
)