package com.otex.homamdriver.view.home.homedriver

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeDeliveryBinding
import com.otex.homamdriver.view.home.HomeActivityViewModel
import com.otex.homamdriver.view.login.UpdatePassowrdActivity
import com.otex.homamdriver.view.order.OrderActivity
import com.otex.homamdriver.view.start.MainActivity
import com.otex.homamuser.utlitites.PrefsUtil
import java.util.*

class HomeDriverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeDeliveryBinding
    private var homeActivityViewModel: HomeActivityViewModel? = null
    var type: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initialize()
        setuptoolbar()

        click()
    }

    private fun setuptoolbar() {
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)

        binding.toolbar.setNavigationOnClickListener { view: View? ->
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)

            }
        }
    }


    override fun onResume() {
        super.onResume()
        getHomeDashBord()

    }

    private fun getHomeDashBord() {

        type= PrefsUtil.with(this).get("type","")!!

            homeActivityViewModel!!.getHomeDriverDashbord(this)

    }


    private fun click() {

        binding.drawer.updatePassword.setOnClickListener {
            val intent= Intent(this, UpdatePassowrdActivity::class.java)
            startActivity(intent)
        }




        binding.waitingbtn.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            intent.putExtra("type","ready_for_delivery")
            startActivity(intent)

        }

        binding.deliveredbtn.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            intent.putExtra("type","completed")
            startActivity(intent)
        }


        binding.ondeliverybtn.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            intent.putExtra("type","on_delivery")
            startActivity(intent)
        }



        binding.drawer.logout.setOnClickListener {
            PrefsUtil.with(this).add("token","").apply()
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initialize() {
        binding.drawer.openCloseBtn.visibility=View.GONE
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        observerHomeDriverDashBord()

    }

    @SuppressLint("SetTextI18n")
    private fun observerHomeDriverDashBord() {
        homeActivityViewModel!!.homeDriverLivedata.observe(this) {

            binding.deliveredCount.text=it.delivered.toString() +" "+getString(R.string.order)
            binding.orderCount.text=it.pending.toString() +" "+getString(R.string.order)
            binding.ondeliveryCount.text=it.on_delivery.toString() +" "+getString(R.string.order)
            binding.totalDeliveryCount.text=it.revenue.toString() +" "+getString(R.string.order)


        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}