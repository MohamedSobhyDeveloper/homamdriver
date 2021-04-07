package com.otex.homamdriver.view.start

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ViewAnimator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.animate
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.otex.homamdriver.databinding.ActivityMainBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.view.login.LoginActivity
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity


class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        anim_btn()
        click()
    }

    private fun anim_btn() {
        YoYo.with(Techniques.ZoomIn)
            .duration(400)
            .repeat(0)
            .playOn(binding.btnStore)
        YoYo.with(Techniques.ZoomIn)
            .duration(400)
            .repeat(0)
            .playOn(binding.btnDriver)
    }

    private fun click() {
        binding.btnStore.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            PrefsUtil.with(this).add("type",Constant.store).apply()
            intent.putExtra("type", Constant.store)
            startActivity(intent)
            finish()
        }

        binding.btnDriver.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            PrefsUtil.with(this).add("type",Constant.driver).apply()
            intent.putExtra("type", Constant.driver)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}