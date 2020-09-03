package com.example.iot_android.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.SingleLiveEvent

class RegisterViewModel : ViewModel() {

    val btn = SingleLiveEvent<Unit>()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    var checkNull = MutableLiveData<Boolean>()

    fun checkNullFun(){
        Log.d("TAG", "NullFUn")
        if(email.value != null && password.value != null)
        {
            Log.d("TAG", "Nullfun")
            checkNull.value = true
        }
        else {
            checkNull.value = false
        }
    }

    fun setData(){
        MyApplication.prefs.setEmail("loginDataEmail", email.value)
        MyApplication.prefs.setPassword("loginDataPassword", password.value)
    }

    fun btnClick(){
        Log.d("TAG", "ca;;")
        btn.call()
    }

}