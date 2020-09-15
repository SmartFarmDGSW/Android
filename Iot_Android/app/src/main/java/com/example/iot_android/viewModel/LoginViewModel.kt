package com.example.iot_android.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.LoginData
import com.example.iot_android.retrofit.InterfaceService
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginViewModel : ViewModel(){

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val username = MutableLiveData<String>()

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
                Log.d("TAG", "t : ${t.message}")
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

    fun l_btnClick(){
        loginBtn.call()
    }
    //
}