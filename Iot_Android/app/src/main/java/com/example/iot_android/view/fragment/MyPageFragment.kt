package com.example.iot_android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.viewModel.MyPageViewModel
import com.example.iot_android.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {

    private lateinit var mypageViewModel: MyPageViewModel
    lateinit var mBinding: FragmentMypageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mainpage, container, false)!!
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelProvider()
    }

    private fun viewModelProvider() {
        mypageViewModel = ViewModelProvider(this)[MyPageViewModel::class.java]
        mBinding.viewModel = mypageViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }
}