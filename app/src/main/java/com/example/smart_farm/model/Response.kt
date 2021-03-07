package com.example.smart_farm.model

//Response Model
data class Response(
    var code: String,
    var message: String,
    var error: String,
    var token: String,
    var success: String
)
