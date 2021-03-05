package com.example.smart_farm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//로그인/회원가입 viewModel
@Suppress("UNCHECKED_CAST")
class SignInViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignInDialogViewModel::class.java)){
            return SignInDialogViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}