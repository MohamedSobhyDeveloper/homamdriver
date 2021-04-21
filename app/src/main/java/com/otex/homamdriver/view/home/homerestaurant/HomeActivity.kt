package com.otex.homamdriver.view.home.homerestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.view.home.HomeActivityViewModel
import com.otex.homamdriver.view.login.LoginActivity
import com.otex.homamdriver.view.order.OrderActivity
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import java.util.*

@Suppress("DEPRECATION")
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var homeActivityViewModel : HomeActivityViewModel? = null
    var type:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initialize()
        getHomeDashBord()
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



    private fun getHomeDashBord() {

        type= PrefsUtil.with(this).get("type","")!!

            homeActivityViewModel!!.getHomeRestaurantDashbord(this)

    }


    private fun click() {


        binding.waitingbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","pending")
            startActivity(intent)

        }

        binding.deliveredbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","delivered")
            startActivity(intent)
        }

        binding.acceptedbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","accepted")
            startActivity(intent)
        }

        binding.canceledbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","canceled")
            startActivity(intent)
        }

        binding.workingOnbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","working_on")
            startActivity(intent)
        }

        binding.readyForbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","ready_for_delivery")
            startActivity(intent)
        }


        binding.drawer.logout.setOnClickListener {
            PrefsUtil.with(this).add("token","").apply()
            val intent =Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initialize() {
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
            observerHomeRestDashBord()

    }

    @SuppressLint("SetTextI18n")
    private fun observerHomeRestDashBord() {
        homeActivityViewModel!!.homeRestaurantLivedata.observe(this) {

            binding.orderCount.text=it.pending.toString() +" "+getString(R.string.order)
            binding.deliveredCount.text=it.delivered.toString()+" "+getString(R.string.order)
            binding.canceledCount.text=it.canceled.toString() +" "+getString(R.string.order)
            binding.accptedCount.text=it.accepted.toString() +" "+getString(R.string.order)
            binding.totalStoreCount.text=it.revenue.toString() +" "+getString(R.string.order)
            binding.orderWorkOnCount.text=it.working_on.toString() +" "+getString(R.string.order)
            binding.orderReadyForCount.text=it.ready_for_delivery.toString() +" "+getString(R.string.order)


        }
    }


}