package com.otex.homamdriver.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        click()
    }

    private fun click() {
        binding.backbtn.setOnClickListener {
            finish()
        }

    }
}