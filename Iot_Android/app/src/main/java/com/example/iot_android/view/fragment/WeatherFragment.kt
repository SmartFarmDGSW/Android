package com.example.iot_android.view.fragment

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.FragmentWeatherBinding
import com.example.iot_android.retrofit.RetrofitClientWeather
import com.example.iot_android.viewModel.WeatherViewModel
import com.example.iot_android.widget.GpsTracker
import com.example.iot_android.widget.extension.toast
import kotlinx.android.synthetic.main.fragment_weather.*
import java.io.IOException
import java.util.*

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

        val address = getCurrentAddress(gpsTracker.getLatitude(), gpsTracker.getLongitude())
        locate.text = address


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

    fun getCurrentAddress(latitude: Double, longitude: Double): String? {

        val geocoder = Geocoder(activity, Locale.getDefault())
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