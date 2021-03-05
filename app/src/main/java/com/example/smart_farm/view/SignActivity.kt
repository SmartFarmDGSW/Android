package com.example.smart_farm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.smart_farm.R
import com.example.smart_farm.databinding.ActivitySignBinding
import com.example.smart_farm.viewmodel.SignViewModel
import com.example.smart_farm.viewmodel.SignViewModelFactory

//로그인/회원가입 Activity
class SignActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivitySignBinding
    private lateinit var viewModel: SignViewModel
    private lateinit var viewModelFactory: SignViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signInDialog = SignInDialog()

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign)

        viewModelFactory = SignViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignViewModel::class.java)

        dataBinding.apply{
            lifecycleOwner = this@SignActivity

            loginButton.setOnClickListener {
                signInDialog.show(supportFragmentManager, "signInDialog")
            }
        }

    }
}