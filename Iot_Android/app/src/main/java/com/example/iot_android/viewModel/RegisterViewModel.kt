package com.example.iot_android.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.model.RegisterBody
import com.example.iot_android.model.RegisterData
import com.example.iot_android.retrofit.Dao
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RegisterViewModel : ViewModel() {
    val btn = SingleLiveEvent<Unit>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    var checkNull = MutableLiveData<Boolean>()

    fun checkNullFun(){
        if(email.value != null && password.value != null)
        {
            checkNull.value = true
        }
        checkNull.value = false
    }

    fun setData(){
        MyApplication.prefs.setEmail("loginDataEmail", email.value)
    }

    fun btnClick(){
        btn.call()
    }

}