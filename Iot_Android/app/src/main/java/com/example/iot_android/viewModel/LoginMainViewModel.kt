package com.example.iot_android.viewModel

import androidx.lifecycle.ViewModel
import com.example.iot_android.widget.SingleLiveEvent

class LoginMainViewModel : ViewModel(){

    val loginBtn = SingleLiveEvent<Unit>()
    val registerBtn = SingleLiveEvent<Unit>()

    fun loginBtnClick(){
        loginBtn.call()
    }

    fun registerBtnClick(){
        registerBtn.call()
    }
}