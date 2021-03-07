package com.example.smart_farm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.smart_farm.R
import com.example.smart_farm.databinding.DialogSignupBinding
import com.example.smart_farm.viewmodel.SignUpDialogViewModel
import com.example.smart_farm.viewmodel.SignUpViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//회원가입 Bottom Sheet 다이얼로그
class SignUpDialog : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = SignUpDialog()
    }

    private lateinit var dataBinding: DialogSignupBinding
    private var viewModel: SignUpDialogViewModel? = null
    private var viewModelFactory: SignUpViewModelFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val signInDialog = SignInDialog()
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_signup, container, false)

        dataBinding.apply{

            myViewModel = viewModel
            lifecycleOwner = this@SignUpDialog

            signIn.setOnClickListener {
                signInDialog.show(fragmentManager!!, "signUpDialog")
                dialog?.dismiss()
            }

            signUpButton.setOnClickListener {
                viewModel!!.signUp()
            }
        }

        return dataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = SignUpViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory!!).get(SignUpDialogViewModel::class.java)
    }
}