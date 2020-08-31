package com.example.iot_android.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.ActivityRegisterBinding
import com.example.iot_android.retrofit.RetrofitClient
import com.example.iot_android.viewModel.RegisterViewModel
import com.example.iot_android.widget.extension.startActivity


class RegisterActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityRegisterBinding
    lateinit var mViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        mViewModel.retrofit = RetrofitClient.getInstance()

        with(mViewModel){
            btn.observe(this@RegisterActivity, Observer {
                register()
            })
            checkLogin.observe(this@RegisterActivity, Observer {
                if (checkLogin.value == true) {
                    Log.d("ASD", "ASD")
                    Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    startActivity(MainActivity::class.java)
                } else if (checkLogin.value == false) {
                    Toast.makeText(applicationContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}