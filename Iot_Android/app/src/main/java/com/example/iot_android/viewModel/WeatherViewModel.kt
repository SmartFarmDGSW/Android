package com.example.iot_android.viewModel

import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iot_android.adapter.WeatherAdapter
import com.example.iot_android.model.weather.WeatherData
import com.example.iot_android.retrofit.InterfaceService
import com.example.iot_android.room.DataBase
import com.example.iot_android.room.WeatherDB
import com.example.iot_android.service.utils.DateManager
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Long
import java.sql.Array
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class WeatherViewModel : ViewModel() {

    var day = MutableLiveData<String>()
    var locate = MutableLiveData<String>()
    var weather = MutableLiveData<String>()
    var temp = MutableLiveData<String>()
    var feelingTemp = MutableLiveData<String>()
    var windSpeed = MutableLiveData<String>()
    var look = MutableLiveData<String>()
    var atmosphericPressure = MutableLiveData<String>()
    var humi = MutableLiveData<String>()
    var dewPoint = MutableLiveData<String>()
    var sunrise = MutableLiveData<String>()
    var sunset = MutableLiveData<String>()

    lateinit var weatherList : WeatherDB

    var weatherDb : DataBase? = null

    fun setData(){
        weatherList = weatherDb?.dao()?.getAll()!!
        temp.value = weatherList.daily?.get(0)?.temp?.day.toString()
        day.value = DateManager.getDate(LocalDateTime.now())
        weather.value = weatherList.daily?.get(0)?.weather?.get(0)?.main.toString()
        feelingTemp.value = weatherList.daily?.get(0)?.feels_like?.day.toString()
        windSpeed.value = weatherList.daily?.get(0)?.wind_speed.toString()
        atmosphericPressure.value = weatherList.daily?.get(0)?.pressure.toString()
        humi.value = weatherList.daily?.get(0)?.humidity.toString()
        Log.d("TAG, ","humi : ${humi.value}")
        dewPoint.value = weatherList.daily?.get(0)?.dew_point.toString()
        sunrise.value = getDate((weatherList.daily?.get(0)?.sunrise)!! * 1000)
        sunset.value = getDate((weatherList.daily?.get(0)?.sunset)!! * 1000)
    }

    fun getDate(milliSecond: Int?) : String{
        return DateFormat.format("hh:mm", Long.parseLong(milliSecond.toString())).toString();
    }




}

