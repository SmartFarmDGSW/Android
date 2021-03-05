package com.example.smart_farm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//Splash Activity
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, SignActivity::class.java))
        finish()
    }
}