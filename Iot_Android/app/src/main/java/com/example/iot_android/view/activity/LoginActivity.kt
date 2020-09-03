package com.example.iot_android.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.ActivityLoginBinding
import com.example.iot_android.retrofit.RetrofitClient
import com.example.iot_android.viewModel.LoginViewModel
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.extension.startActivity
import com.example.iot_android.widget.extension.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityLoginBinding
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        mViewModel.retrofit = RetrofitClient.getInstance()

        with(mViewModel){
            loginBtn.observe(this@LoginActivity, Observer {
                login()
            })

            status.observe(this@LoginActivity, Observer {
                if(status.value == 200) {
                    toast("로그인 성공")
                    Log.d("TAG", MyApplication.prefs.getToken("token", "없음"))
                    startActivity(MainActivity::class.java)
                }
                else {
                    toast("로그인 실패")
                }
            })
        }

    }
}


