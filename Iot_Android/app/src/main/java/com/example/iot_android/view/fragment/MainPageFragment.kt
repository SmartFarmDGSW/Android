package com.example.iot_android.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.databinding.FragmentMainpageBinding
import com.example.iot_android.viewModel.MainPageViewModel
import com.example.iot_android.retrofit.RetrofitClient
import com.example.iot_android.widget.MyApplication

class MainPageFragment : Fragment() {

    lateinit var mBinding: FragmentMainpageBinding
    lateinit var mainPageViewModel: MainPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mainpage, container, false)!!
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelProvider()
        init()
        observerViewModel()
    }

    private fun init() {

        Log.d("토큰", MyApplication.prefs.getToken("token", "없음"))
    }

    private fun viewModelProvider() {
        mainPageViewModel = ViewModelProvider(this)[MainPageViewModel::class.java]
        mBinding.viewModel = mainPageViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun observerViewModel() {
        with(mainPageViewModel) {

        }
    }
}
