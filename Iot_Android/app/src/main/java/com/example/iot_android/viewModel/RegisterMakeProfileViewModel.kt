package com.example.iot_android.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.model.RegisterBody
import com.example.iot_android.model.RegisterData
import com.example.iot_android.retrofit.InterfaceService
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RegisterMakeProfileViewModel : ViewModel() {

    var username = MutableLiveData<String>()
    val btn = SingleLiveEvent<Unit>()
    var checkNull = MutableLiveData<Boolean>()
    var status = MutableLiveData<String>()

    lateinit var myAPI : InterfaceService
    lateinit var retrofit: Retrofit

    fun checkNullFun(){
        checkNull.value = username.value != null
    }

    fun register(){
        myAPI = retrofit.create(InterfaceService::class.java)
        myAPI.register(RegisterBody(username = username.value.toString(),
                                        email = MyApplication.prefs.getEmail("loginDataEmail", ""),
                                        password = MyApplication.prefs.getPassword("loginDataPassword", ""))
        ).enqueue(object : Callback<RegisterData> {
            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                status.value = t.message
                Log.d("ASD", "DDD");
            }

            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                status.value = response.code().toString()
            }
        })
    }


    fun btnClick(){
        btn.call()
    }
}