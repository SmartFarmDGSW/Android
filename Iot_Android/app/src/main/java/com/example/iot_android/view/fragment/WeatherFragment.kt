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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.FragmentWeatherBinding
import com.example.iot_android.retrofit.RetrofitClient
import com.example.iot_android.retrofit.RetrofitClientWeather
import com.example.iot_android.viewModel.WeatherViewModel
import com.example.iot_android.widget.GpsTracker

class WeatherFragment : Fragment() {

    private lateinit var gpsTracker: GpsTracker
    lateinit var mBinding: FragmentWeatherBinding
    lateinit var mViewModel: WeatherViewModel

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

    private fun init() {


        gpsTracker = GpsTracker(this)
        gpsTracker.getLocation(requireContext())

        mViewModel.latitude.value = gpsTracker.getLatitude().toInt()
        mViewModel.longitude.value = gpsTracker.getLongitude().toInt()

        mViewModel.retrofit = RetrofitClientWeather.getInstance()
    }


    private fun viewModelProvider() {
        mViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun observerViewModel() {
        with(mViewModel) {
            latitude.observe(viewLifecycleOwner, Observer {
                getWeather()
            })
        }
    }
}