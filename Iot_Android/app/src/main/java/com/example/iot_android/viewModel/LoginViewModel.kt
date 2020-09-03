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

    var checkLogin = MutableLiveData<Boolean>()

    lateinit var myAPI : InterfaceService
    lateinit var retrofit: Retrofit

    fun login() {
        myAPI = retrofit.create(InterfaceService::class.java)
        myAPI.login(LoginBody(username = username.value.toString(), email = email.value.toString(), password = password.value.toString())).enqueue(object :
            Callback<LoginData> {
            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                checkLogin.value = false
            }
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                Log.d("TAG", username.value.toString())
                Log.d("TAG", email.value.toString())
                Log.d("TAG", password.value.toString())
                Log.d("TAG", response.code().toString())
                Log.d("TAG", "성공")
                checkLogin.value = true
                MyApplication.prefs.setUsername("name", username.value.toString())
                MyApplication.prefs.setEmail("email", email.value.toString())
                MyApplication.prefs.setToken("token", response.body()?.key)
            }
        })
    }

    fun l_btnClick(){
        loginBtn.call()
    }
    //
}