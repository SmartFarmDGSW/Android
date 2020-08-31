package com.example.iot_android.model

data class RegisterBody(
    val username: String,
    val email: String,
    val password1: String,
    val password2: String
)