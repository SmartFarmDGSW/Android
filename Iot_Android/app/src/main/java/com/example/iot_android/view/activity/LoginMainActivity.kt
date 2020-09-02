package com.example.iot_android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.ActivityLoginBinding
import com.example.iot_android.databinding.ActivityLoginMainBinding
import com.example.iot_android.viewModel.LoginMainViewModel
import com.example.iot_android.viewModel.LoginViewModel
import com.example.iot_android.widget.extension.startActivity

class LoginMainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityLoginMainBinding
    lateinit var mViewModel: LoginMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_main)
        mViewModel = ViewModelProvider(this)[LoginMainViewModel::class.java]

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        with(mViewModel){
            loginBtn.observe(this@LoginMainActivity, Observer {
                startActivity(LoginActivity::class.java)
            })

            registerBtn.observe(this@LoginMainActivity, Observer {
                startActivity(RegisterActivity::class.java)
            })
        }
    }
}