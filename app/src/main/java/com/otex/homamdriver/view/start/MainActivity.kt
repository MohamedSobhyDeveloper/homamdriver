package com.otex.homamdriver.view.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.databinding.ActivityMainBinding
import com.otex.homamdriver.view.home.HomeActivity
import com.otex.homamdriver.view.login.LoginActivity
import com.otex.homamdriver.view.order.OrderActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        click()
    }

    private fun click() {
        binding.btnStore.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("type", "store")
            startActivity(intent)
        }

        binding.btnDriver.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("type", "driver")
            startActivity(intent)
        }
    }
}