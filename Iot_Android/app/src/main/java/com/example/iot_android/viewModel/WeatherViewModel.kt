package com.example.iot_android.viewModel

import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.R
import com.example.iot_android.model.weather.WeatherData
import com.example.iot_android.retrofit.InterfaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel : ViewModel() {

    var latitude = MutableLiveData<Int>()
    var longitude = MutableLiveData<Int>()

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
        myAPI.getWeather(
            lat = latitude.value!!,
            lon = longitude.value!!,
            appId = "beb156f9ab48aafb74a83023fab39ef3",
            exclude = "minutely,current,hourly",
            unites = "metric"
        ).enqueue(object :
            Callback<WeatherData> {
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("TAG", "t : ${t.message}")
            }

            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                Log.d("TAG", "code ${response.code()}" )
                Log.d("TAG", "code ${response.message()}" )
                locate.value = response.body()?.timezone
                temp.value = response.body()?.daily?.get(0)?.temp?.day.toString()
                feelingTemp.value = response.body()?.daily?.get(0)?.feels_like?.day.toString()
                windSpeed.value = response.body()?.daily?.get(0)?.wind_speed.toString()
                atmosphericPressure.value = response.body()?.daily?.get(0)?.pressure.toString()
                humi.value = response.body()?.daily?.get(0)?.humidity.toString()
                Log.d("TAG, ","humi : ${humi.value}")
                dewPoint.value = response.body()?.daily?.get(0)?.dew_point.toString()
                Log.d("TAG", "sunrise : ${response.body()?.daily?.get(0)?.sunrise}")
                Log.d("TAG", "sunrise : ${getDate((response.body()?.daily?.get(0)?.sunrise))}")
                sunrise.value = getDate((response.body()?.daily?.get(0)?.sunrise)!! * 1000)
                Log.d("TAG", "sunset : ${response.body()?.daily?.get(0)?.sunset}")
                Log.d("TAG", "sunset : ${getDate(response.body()?.daily?.get(0)?.sunset)}")
                sunset.value = getDate((response.body()?.daily?.get(0)?.sunset)!! * 1000)
            }
        })
    }

    fun getDate(milliSecond: Int?) : String{
        return DateFormat.format("hh:mm", Long.parseLong(milliSecond.toString())).toString();
    }
}