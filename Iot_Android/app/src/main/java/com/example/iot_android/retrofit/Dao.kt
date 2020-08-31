package com.example.iot_android.retrofit

import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Dao {
    @POST("/auth/login/")
    fun getlogindata(
        @Body loginBody: LoginBody
    ) : Call<Register>

    @POST("/auth/registration/")
    fun sendregister(
        @Body body: com.example.iot_android.model.Body
    ) :Call<Register>
}