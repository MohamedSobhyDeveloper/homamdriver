package com.otex.homamdriver.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamdriver.databinding.ActivityLoginBinding
import com.otex.homamdriver.view.home.HomeActivity


class LoginActivity : AppCompatActivity() {

    private var loginviewmodel : LoginActivityViewModel? = null
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        click()

    }

    private fun click() {


        binding.backbtn.setOnClickListener {
            finish()
        }

        binding.txtForgoPassword.setOnClickListener {
//            startActivity(Intent(this, ActivityForgetPassword::class.java))

        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }


}