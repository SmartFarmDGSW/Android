package com.example.iot_android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.iot_android.R
import com.example.iot_android.viewModel.ManageViewModel
import com.example.iot_android.databinding.FragmentManageBinding

class ManageFragment : Fragment() {

    private lateinit var manageViewModel: ManageViewModel
    lateinit var mBinding: FragmentManageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mainpage, container, false)!!
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelProvider()
    }

    private fun viewModelProvider() {
        manageViewModel = ViewModelProvider(this)[ManageViewModel::class.java]
        mBinding.viewModel = manageViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

}
