package com.example.smart_farm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.smart_farm.R
import com.example.smart_farm.databinding.DialogSigninBinding
import com.example.smart_farm.viewmodel.SignInDialogViewModel
import com.example.smart_farm.viewmodel.SignInViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//로그인 BottomSheet 다이얼로그
class SignInDialog : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = SignInDialog()
    }

    private lateinit var dataBinding: DialogSigninBinding
    private var viewModel: SignInDialogViewModel? = null
    private var viewModelFactory: SignInViewModelFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val signUpDialog = SignUpDialog()
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_signin, container, false)

        dataBinding.apply {
            myViewModel = viewModel

            lifecycleOwner = this@SignInDialog

            signUp.setOnClickListener {
                signUpDialog.show(fragmentManager!!, "signUpDialog")
                dialog?.dismiss()
            }

            loginButton.setOnClickListener {
                viewModel!!.signIn()
            }
        }

        viewModel.apply {
        }

        return dataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = SignInViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory!!).get(SignInDialogViewModel::class.java)
    }
}