package com.example.iot_android.view.activity

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.ActivityLoginBinding
import com.example.iot_android.retrofit.RetrofitClient
import com.example.iot_android.retrofit.RetrofitClientWeather
import com.example.iot_android.viewModel.LoginViewModel
import com.example.iot_android.widget.GpsTracker
import com.example.iot_android.widget.MyApplication
import com.example.iot_android.widget.extension.startActivity
import com.example.iot_android.widget.extension.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_weather.*
import java.io.IOException
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var gpsTracker: GpsTracker
    lateinit var mBinding: ActivityLoginBinding
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        gpsTracker = GpsTracker()
        gpsTracker.getLocation(applicationContext)

        mViewModel.latitude.value = gpsTracker.getLatitude().toInt()
        mViewModel.longitude.value = gpsTracker.getLongitude().toInt()

        val address = getCurrentAddress(gpsTracker.getLatitude(), gpsTracker.getLongitude())
        locate.text = address


        mViewModel.retrofit = RetrofitClient.getInstance()

        with(mViewModel){
            loginBtn.observe(this@LoginActivity, Observer {
                login()
            })

            status.observe(this@LoginActivity, Observer {
                if(status.value == 200) {
                    mViewModel.retrofit = RetrofitClientWeather.getInstance()
                    getWeather()
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

    fun getCurrentAddress(latitude: Double, longitude: Double): String? {

        val geocoder = Geocoder(applicationContext, Locale.getDefault())
        val addresses: List<Address>
        addresses = try {
            geocoder.getFromLocation(
                latitude,
                longitude,
                7
            )
        } catch (ioException: IOException) {
            //네트워크 문제
            toast("지오코더 서비스 사용불가")
            return "지오코더 서비스 사용불가"
        } catch (illegalArgumentException: IllegalArgumentException) {
            toast("잘못된 GPS 좌표")
            return "잘못된 GPS 좌표"
        }
        if (addresses == null || addresses.size == 0) {
            toast("주소 미발견")
            return "주소 미발견"
        }
        val address: Address = addresses[0]

        return address.adminArea.replace("광역시", "")
    }
}


