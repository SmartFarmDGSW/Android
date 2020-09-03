package com.example.iot_android.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iot_android.R
import com.example.iot_android.databinding.FragmentWeatherBinding
import com.example.iot_android.viewModel.WeatherViewModel
import com.example.iot_android.widget.GpsTracker

class WeatherFragment : Fragment() {

    private lateinit var gpsTracker: GpsTracker
    lateinit var mBinding: FragmentWeatherBinding
    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)!!
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelProvider()
        init()
        observerViewModel()
    }

    fun init() {
        if(!checkLocationServicesStatus())
            showDialogForLocationServiceSetting()

        gpsTracker = GpsTracker(this)
        gpsTracker.getLocation(requireContext())

        val latitude = gpsTracker!!.getLatitude()
        val longitude = gpsTracker!!.getLongitude()

        Log.d("TAG", "위도 : $latitude, 경도 : $longitude")
    }


    private fun viewModelProvider() {
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        mBinding.viewModel = weatherViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun observerViewModel() {
        with(weatherViewModel) {
            getLocation()
        }
    }

    fun checkLocationServicesStatus(): Boolean {
        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    private fun showDialogForLocationServiceSetting() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
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