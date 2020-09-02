package com.example.iot_android.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.iot_android.R
import com.example.iot_android.widget.extension.startActivity
import com.example.iot_android.widget.extension.toast

class SplashActivity : AppCompatActivity() {

    var permission_list = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkPermission()
        startActivity(LoginMainActivity::class.java)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun checkPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        for (permission in permission_list) {
            val chk = checkCallingOrSelfPermission(permission!!)
            if (chk == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permission_list, 0)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(LoginMainActivity::class.java)
                } else {
                    toast("퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요")
                    finish()
                }
            }
        }
    }
}