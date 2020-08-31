package com.example.iot_android.retrofit

import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.LoginData
import com.example.iot_android.model.RegisterBody
import com.example.iot_android.model.RegisterData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Dao {
    @POST("/auth/login/")
    fun getlogindata(
        @Body loginBody: LoginBody
    ) : Call<LoginData>

    @POST("/auth/registration/")
    fun sendregister(
        @Body body: RegisterBody
    ) :Call<RegisterData>
}