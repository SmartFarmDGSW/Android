package com.example.iot_android.model

data class LoginData(
    val token : String,
    val user: User
) {
    data class User(
        val pk : String,
        val username : String,
        val email: String,
        val first_name: String,
        val last_name: String
    )
}
