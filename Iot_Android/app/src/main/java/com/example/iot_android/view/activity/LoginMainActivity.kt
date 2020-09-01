package com.example.iot_android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.ActivityLoginBinding
import com.example.iot_android.databinding.ActivityLoginMainBinding
import com.example.iot_android.viewModel.LoginMainViewModel
import com.example.iot_android.viewModel.LoginViewModel

class LoginMainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityLoginMainBinding
    lateinit var mViewModel: LoginMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mViewModel = ViewModelProvider(this)[LoginMainViewModel::class.java]

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

    }
}