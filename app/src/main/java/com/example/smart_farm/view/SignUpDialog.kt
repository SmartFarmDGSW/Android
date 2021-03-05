package com.example.smart_farm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.smart_farm.R
import com.example.smart_farm.databinding.DialogSignupBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SignUpDialog : BottomSheetDialogFragment() {

    private lateinit var dataBinding: DialogSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_signup, container, false)
        val signInDialog = SignInDialog()

        with(dataBinding){
            signIn.setOnClickListener {
                signInDialog.show(fragmentManager!!, "signInDialog")
                dialog?.dismiss()
            }
        }

        return dataBinding.root
    }
}