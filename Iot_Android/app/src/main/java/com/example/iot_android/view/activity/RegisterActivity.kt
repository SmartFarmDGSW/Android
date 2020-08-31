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
import com.example.iot_android.widget.extension.toast


class RegisterActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityRegisterBinding
    lateinit var mViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        with(mViewModel){
            btn.observe(this@RegisterActivity, Observer {
                checkNullFun()
            })
            checkNull.observe(this@RegisterActivity, Observer {
                if(checkNull.value == false)
                {
                    toast("모두 값을 입력해주세요.")
                }
                else{
                    setData()
                    startActivity(RegisterMakeProfileActivity::class.java)
                }

            })
        }

    }
}