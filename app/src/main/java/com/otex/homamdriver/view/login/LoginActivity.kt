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

        val type=intent.getStringExtra("type")

        binding.backbtn.setOnClickListener {
            finish()
        }

        binding.txtForgoPassword.setOnClickListener {
//            startActivity(Intent(this, ActivityForgetPassword::class.java))

        }

        binding.btnLogin.setOnClickListener {
            if(type.equals("store")) {

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("type","store")
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("type","driver")
                startActivity(intent)
                finish()
            }
        }
    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }


}