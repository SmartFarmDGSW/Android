package com.example.iot_android.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.LoginData
import com.example.iot_android.model.weather.WeatherData
import com.example.iot_android.retrofit.InterfaceService
import com.example.iot_android.widget.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class WeatherViewModel : ViewModel() {

    var latitude = MutableLiveData<Double>()
    var longitude = MutableLiveData<Double>()

    var day = MutableLiveData<String>()
    var locate = MutableLiveData<String>()
    var weather_image = MutableLiveData<String>()
    var temp = MutableLiveData<String>()
    var feelingTemp = MutableLiveData<String>()
    var windSpeed = MutableLiveData<String>()
    var look = MutableLiveData<String>()
    var atmosphericPressure = MutableLiveData<String>()
    var humi = MutableLiveData<String>()
    var dewPoint = MutableLiveData<String>()
    var sunrise = MutableLiveData<String>()
    var sunset = MutableLiveData<String>()



    lateinit var myAPI : InterfaceService
    lateinit var retrofit: Retrofit

    fun getWeather(){
        myAPI = retrofit.create(InterfaceService::class.java)
        myAPI.getWeather(lat = latitude.value.toString(), lon = longitude.value.toString()).enqueue(object :
            Callback<WeatherData> {
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
            }
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {

            }
        })
    }



}