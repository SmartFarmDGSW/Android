package com.example.iot_android.widget

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getEmail(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun setEmail(key: String, str: String?)
    {
        prefs.edit().putString(key, str).apply()
    }

    fun getToken(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun setToken(key: String, str: String?)
    {
        prefs.edit().putString(key, str).apply()
    }

    fun getUsername(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun setUsername(key: String, str: String?)
    {
        prefs.edit().putString(key, str).apply()
    }

    fun getPassword(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun setPassword(key: String, str: String?)
    {
        prefs.edit().putString(key, str).apply()
    }


}