package com.otex.homamdriver.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.databinding.ActivitySplashBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.view.home.HomeActivity
import com.otex.homamdriver.view.login.LoginActivity
import com.otex.homamdriver.view.start.MainActivity
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    var signstate:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({

            signstate= PrefsUtil.with(this)["token", ""].toString()
            if(signstate.isNotEmpty()) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }, 2000)
    }
}