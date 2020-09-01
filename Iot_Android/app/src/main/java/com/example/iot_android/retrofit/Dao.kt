package com.example.iot_android.retrofit

import com.example.iot_android.R
import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.LoginData
import com.example.iot_android.model.RegisterBody
import com.example.iot_android.model.RegisterData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Dao {
    @POST("/auth/login/")
    fun login(
        @Body loginBody: LoginBody
    ) : Call<LoginData>

    @POST("/auth/registration/")
    fun register(
        @Body body: RegisterBody
    ) :Call<RegisterData>

    @GET("lat=35.441792&lon=127.689&units=metric&exclude=minutely,current,hourly&appid=${R.string.api_key}")
    fun getWeather()
}