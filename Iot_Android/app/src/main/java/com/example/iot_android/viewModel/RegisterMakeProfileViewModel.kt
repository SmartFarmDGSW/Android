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
    var checkRegister = MutableLiveData<Boolean>()

    lateinit var myAPI : InterfaceService
    lateinit var retrofit: Retrofit

    fun checkNullFun(){
        checkNull.value = username.value != null
    }

    fun register(){
        myAPI = retrofit.create(InterfaceService::class.java)
        myAPI.register(RegisterBody(username = username.value.toString(),
                                        email = MyApplication.prefs.getEmail("loginDataEmail", ""),
                                        password1 = MyApplication.prefs.getPassword("loginDataPassword", ""),
                                        password2 = MyApplication.prefs.getPassword("loginDataPassword", ""))
        ).enqueue(object : Callback<RegisterData> {
            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                checkRegister.value = false
                Log.d("ASD", "DDD");
            }

            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                checkRegister.value = true
                MyApplication.prefs.setToken("token", response.body()?.key)
                Log.d("key", "pk:"+response.body()?.key)
            }
        })
    }


    fun btnClick(){
        btn.call()
    }
}