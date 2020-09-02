package com.example.iot_android.view.fragment

import android.os.Bundle
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
}