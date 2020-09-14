package com.example.iot_android.retrofit

import com.example.iot_android.R
import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.LoginData
import com.example.iot_android.model.RegisterBody
import com.example.iot_android.model.RegisterData
import com.example.iot_android.model.weather.WeatherData
import retrofit2.Call
import retrofit2.http.*

interface InterfaceService {
    @POST("/auth/login/")
    fun login(
        @Body loginBody: LoginBody
    ) : Call<LoginData>

    @POST("/auth/registration/")
    fun register(
        @Body body: RegisterBody
    ) :Call<RegisterData>

    @GET("/onecall")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") unites: String,
        @Query("exclude") exclude : String,
        @Query("appid") appId : String
        ) :Call<WeatherData>
}