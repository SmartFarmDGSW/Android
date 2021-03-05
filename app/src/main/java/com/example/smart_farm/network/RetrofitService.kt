package com.example.smart_farm.network

import com.example.smart_farm.model.Response
import com.example.smart_farm.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    //회원가입
    @POST("/auth/sign-up")
    fun signUp(
        @Body userBody: User
    ): Call<Response>

    //로그인
    @POST("/auth/login")
    fun signIn(
        @Body userBody: User
    ): Call<Response>

}