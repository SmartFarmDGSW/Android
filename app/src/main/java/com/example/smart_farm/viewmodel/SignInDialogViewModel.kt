package com.example.smart_farm.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.annotation.Dimension
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
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

//로그인 ViewModel
class SignInDialogViewModel : ViewModel() {

    //로그인에 필요한 변수들
    private var name = MutableLiveData<String>()
    var nameData: LiveData<String>
        get() = name
        set(value: LiveData<String>){
            name.value = value.toString()
        }

    private var email = MutableLiveData<String>()
    var emailData: LiveData<String>
        get() = email
        set(value: LiveData<String>){
            email.value = value.toString()
        }

    private var password = MutableLiveData<String>()
    var passwordData: LiveData<String>
        get() = password
        set(value: LiveData<String>){
            password.value = value.toString()
        }

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
    fun signIn() {

        userBody = User(this.name.value.toString(), this.email.value.toString(), this.password.value.toString())

        CoroutineScope(Dispatchers.IO).launch {
            retrofitService.signIn(userBody)
                .enqueue(object : Callback<Response>{
                    override fun onResponse(
                        call: Call<Response>,
                        response: retrofit2.Response<Response>
                    ) {
                        Log.d("retrofit", "success ${response.message()}")
                    }

                    override fun onFailure(call: Call<Response>, t: Throwable) {
                        Log.d("retrofit", "fail ${t.cause}")
                    }
                })
        }
    }
}