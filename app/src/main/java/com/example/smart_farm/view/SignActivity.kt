package com.example.smart_farm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.smart_farm.R
import com.example.smart_farm.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign)
        val signInDialog = SignInDialog()

        with(binding){
            loginButton.setOnClickListener {
                signInDialog.show(supportFragmentManager, "signInDialog")
            }
        }

    }
}