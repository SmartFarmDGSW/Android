package com.example.iot_android.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
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

        if(!checkLocationServicesStatus())
            showDialogForLocationServiceSetting()

        with(mViewModel){
            loginBtn.observe(this@LoginMainActivity, Observer {
                startActivity(LoginActivity::class.java)
            })

            registerBtn.observe(this@LoginMainActivity, Observer {
                startActivity(RegisterActivity::class.java)
            })
        }
    }

    fun checkLocationServicesStatus(): Boolean {
        val locationManager = this?.getSystemService(LOCATION_SERVICE) as LocationManager?
        return (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    private fun showDialogForLocationServiceSetting() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage(
            """
                앱을 사용하기 위해서는 위치 서비스가 필요합니다.
                위치 설정을 수정하실래요?
                """.trimIndent()
        )
        builder.setCancelable(true)
        builder.setPositiveButton("설정", DialogInterface.OnClickListener { dialog, id ->
            val callGPSSettingIntent =
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(callGPSSettingIntent)
        })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }
}