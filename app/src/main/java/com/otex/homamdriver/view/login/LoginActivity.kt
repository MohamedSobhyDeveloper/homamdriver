package com.otex.homamdriver.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityLoginBinding
import com.otex.homamdriver.view.home.HomeActivity
import com.otex.homamdriver.view.start.MainActivity


class LoginActivity : AppCompatActivity() {

    private var loginviewmodel : LoginActivityViewModel? = null
    lateinit var binding: ActivityLoginBinding
    var email_or_phone:String=""
    var password:String=""
    var type:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        click()

    }

    private fun click() {

         type= intent.getStringExtra("type").toString()

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


        binding.btnLogin.setOnClickListener {
            email_or_phone=binding.editEmailPhone.text.toString()
            password=binding.editPassword.text.toString()
            if(email_or_phone.equals("")){
                binding.editEmailPhone.setError(getString(R.string.enter_email))
            }else if(password.equals("")){
                binding.editPassword.setError(getString(R.string.enter_password))
            }else{

                login(email_or_phone,password)
            }

        }
    }

    private fun login(emailOrPhone: String, password: String) {
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

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }


}