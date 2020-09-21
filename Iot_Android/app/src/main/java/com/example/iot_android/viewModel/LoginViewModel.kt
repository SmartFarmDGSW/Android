package com.example.iot_android.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.LoginData
import com.example.iot_android.model.weather.WeatherData
import com.example.iot_android.retrofit.InterfaceService
import com.example.iot_android.room.DataBase
import com.example.iot_android.room.WeatherDB
import com.example.iot_android.service.utils.DateManager
import com.example.iot_android.service.utils.DateManager.getDate
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDateTime

class LoginViewModel : ViewModel(){

    var weatherDb: DataBase? = null

    var latitude = MutableLiveData<Int>()
    var longitude = MutableLiveData<Int>()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val loginBtn = SingleLiveEvent<Unit>()
    var status = MutableLiveData<Int>()

    lateinit var myAPI : InterfaceService
    lateinit var retrofit: Retrofit

    fun login() {
        myAPI = retrofit.create(InterfaceService::class.java)
        myAPI.login(LoginBody(email = email.value.toString(), password = password.value.toString())).enqueue(object :
            Callback<LoginData> {
            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                status.value = 400
            }
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                Log.d("TAG", "성공")
                status.value = response.code()
                Log.d("TAG", "status : ${status.value.toString()}")
                Log.d("TAG", "status : ${response.message()}")
                MyApplication.prefs.setToken("token", response.body()?.token)
            }
        })
    }

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

                val newWetger = WeatherDB(
                    id = 0,
                    lon = response.body()?.lon,
                    lat = response.body()?.lat,
                    daily = response.body()?.daily,
                    timezone = response.body()?.timezone,
                    timezone_offset = response.body()?.timezone_offset
                )

                weatherDb?.dao()?.insert(newWetger)
                Log.d("TAG", "code ${response.code()}" )
                Log.d("TAG", "code ${response.message()}" )
            }
        })
    }

    fun l_btnClick(){
        loginBtn.call()
    }
    //
}