package com.otex.homamdriver.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.otex.homamdriver.databinding.ActivitySplashBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.utlitites.HelpMe
import com.otex.homamdriver.view.home.homedriver.HomeDriverActivity
import com.otex.homamdriver.view.home.homerestaurant.HomeActivity
import com.otex.homamdriver.view.start.MainActivity
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    var signstate:String=""
    var type:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PrefsUtil.with(this).add("device_id", HelpMe.getInstance(this)?.getDeviceId()).apply()


        Handler().postDelayed({

            signstate= PrefsUtil.with(this)["token", ""].toString()
            type= PrefsUtil.with(this)["type", ""].toString()
            if(signstate.isNotEmpty()) {
                if(type==Constant.store){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
                }else{
                    startActivity(Intent(this, HomeDriverActivity::class.java))
                    finish()
                }
            }else{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }, 2000)
    }
}