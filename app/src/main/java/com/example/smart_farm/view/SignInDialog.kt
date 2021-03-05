package com.example.smart_farm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.smart_farm.R
import com.example.smart_farm.databinding.DialogSigninBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SignInDialog : BottomSheetDialogFragment() {

    private lateinit var dataBinding: DialogSigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val signUpDialog = SignUpDialog()
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_signin, container, false)

        with(dataBinding){
            signUp.setOnClickListener {
                signUpDialog.show(fragmentManager!!, "signUpDialog")
                dialog?.dismiss()
            }
        }

        return dataBinding.root
    }
}