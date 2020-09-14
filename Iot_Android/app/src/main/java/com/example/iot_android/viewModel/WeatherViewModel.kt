package com.example.iot_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.R
import com.example.iot_android.model.weather.WeatherData
import com.example.iot_android.retrofit.InterfaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat

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
        myAPI.getWeather(
            lat = latitude.value!!.toDouble(),
            lon = longitude.value!!.toDouble(),
            appId = R.string.api_key.toString(),
            exclude = "minutely,current,hourly",
            unites = "metric"
        ).enqueue(object :
            Callback<WeatherData> {
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
            }

            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                locate.value = response.body()?.timezone
                temp.value = response.body()?.daily?.get(0)?.temp?.day.toString()
                feelingTemp.value = response.body()?.daily?.get(0)?.feels_like?.day.toString()
                windSpeed.value = response.body()?.daily?.get(0)?.wind_speed.toString()
                atmosphericPressure.value = response.body()?.daily?.get(0)?.pressure.toString()
                humi.value = response.body()?.daily?.get(0)?.humidity.toString()
                dewPoint.value = response.body()?.daily?.get(0)?.dew_point.toString()
                sunrise.value = getDate(response.body()?.daily?.get(0)?.sunrise!!)
                sunset.value = getDate(response.body()?.daily?.get(0)?.sunset!!)
            }
        })
    }

    fun getDate(long: Int) : String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return simpleDateFormat.format(long)
    }



}