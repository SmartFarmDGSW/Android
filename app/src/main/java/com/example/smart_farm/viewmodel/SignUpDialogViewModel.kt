package com.example.smart_farm.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smart_farm.model.Response
import com.example.smart_farm.model.User
import com.example.smart_farm.network.RetrofitClient
import com.example.smart_farm.network.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit

//회원가입 ViewModel
class SignUpDialogViewModel() : ViewModel() {

    //회원가입에 필요한 변수들
    private var name = MutableLiveData<String>()
    val nameData: LiveData<String>
        get() = name

    private var email = MutableLiveData<String>()
    val emailData: LiveData<String>
        get() = email

    private var password = MutableLiveData<String>()
    val passwordData: LiveData<String>
        get() = password

    //변수 초기화
    init {
        name.value = ""
        email.value = ""
        password.value = ""
    }

    //Retrofit 설정
    private val retrofit: Retrofit = RetrofitClient.getInstance()
    private var retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)

    //네트워크 통신 Body
    private lateinit var userBody: User

    //로그인 함수
    fun signUp() {

        userBody = User(this.email.value.toString(), this.password.value.toString(), this.name.value.toString())

        CoroutineScope(Dispatchers.IO).launch {
            retrofitService.signUp(userBody)
                .enqueue(object : Callback<Response> {
                    override fun onResponse(
                        call: Call<Response>,
                        response: retrofit2.Response<Response>
                    ) {
                        Log.d("retrofit", "error ${response.errorBody()!!.string()}")
                        Log.d("retrofit", "error ${response.message()}")
                        Log.d("retrofit", "success ${response.code()}")
                    }

                    override fun onFailure(call: Call<Response>, t: Throwable) {
                        Log.d("retrofit", "fail ${t.cause}")
                    }
                })
        }
    }

}