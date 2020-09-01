package com.example.iot_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.model.LoginBody
import com.example.iot_android.model.LoginData
import com.example.iot_android.retrofit.Dao
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.SingleLiveEvent
import com.example.iot_android.model.RegisterData
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

    lateinit var myAPI : Dao
    lateinit var retrofit: Retrofit

    fun login() {
        myAPI = retrofit.create(Dao::class.java)
        myAPI.login(LoginBody(username = username.value.toString(), email = email.value.toString(), password = password.value.toString())).enqueue(object :
            Callback<LoginData> {
            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                checkLogin.value = false
            }
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {

                checkLogin.value = true
                MyApplication.prefs.setUsername("name", response.body()?.user?.username.toString())
                MyApplication.prefs.setEmail("email", response.body()?.user?.email.toString())
                MyApplication.prefs.setToken("token", response.body()?.token.toString())
            }
        })
    }

    fun l_btnClick(){
        loginBtn.call()
    }
    //
}