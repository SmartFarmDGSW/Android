package com.example.iot_android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.ActivityRegisterBinding
import com.example.iot_android.databinding.ActivityRegisterMakeProfileBinding
import com.example.iot_android.retrofit.RetrofitClient
import com.example.iot_android.viewModel.RegisterMakeProfileViewModel
import com.example.iot_android.viewModel.RegisterViewModel
import com.example.iot_android.widget.extension.startActivity
import com.example.iot_android.widget.extension.toast

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

        mViewModel.retrofit = RetrofitClient.getInstance()

        with(mViewModel){
            btn.observe(this@RegisterMakeProfileActivity, Observer {
                checkNullFun()
            })

            checkNull.observe(this@RegisterMakeProfileActivity, Observer {
                if(checkNull.value == false)
                {
                    Log.d("TAG", "이름")
                    toast("이름을 입력해주세요")
                }
                else{
                    Log.d("TAG", "시작서버")
                    register()
                }
            })

            checkRegister.observe(this@RegisterMakeProfileActivity, Observer {
                if(checkRegister.value == false)
                {
                    toast("회원가입 실패")
                    startActivity(LoginMainActivity::class.java)
                }
                else{
                    toast("회원가입 성공")
                    startActivity(MainActivity::class.java)
                }
            })
        }
    }
}