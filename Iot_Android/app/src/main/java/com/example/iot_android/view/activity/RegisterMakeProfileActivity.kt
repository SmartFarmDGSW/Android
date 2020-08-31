package com.example.iot_android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.ActivityRegisterBinding
import com.example.iot_android.databinding.ActivityRegisterMakeProfileBinding
import com.example.iot_android.viewModel.RegisterMakeProfileViewModel
import com.example.iot_android.viewModel.RegisterViewModel

class RegisterMakeProfileActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityRegisterMakeProfileBinding
    lateinit var mViewModel: RegisterMakeProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_make_profile)
        mViewModel = ViewModelProvider(this)[RegisterMakeProfileViewModel::class.java]

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }
}