package com.example.iot_android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iot_android.R
import com.example.iot_android.widget.extension.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(LoginMainActivity::class.java)
    }
}