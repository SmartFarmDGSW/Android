package com.example.iot_android.widget

import android.app.Application

class MyApplication : Application() {

    companion object { lateinit var prefs: PreferenceUtil }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}