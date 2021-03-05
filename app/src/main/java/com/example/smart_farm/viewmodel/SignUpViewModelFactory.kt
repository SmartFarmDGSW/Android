package com.example.smart_farm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//로그인/회원가입 viewModel
@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignUpDialogViewModel::class.java)){
            return SignUpDialogViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}