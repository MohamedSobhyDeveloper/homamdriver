package com.otex.homamdriver.view.home.homerestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.view.home.HomeActivityViewModel
import com.otex.homamdriver.view.login.UpdatePassowrdActivity
import com.otex.homamdriver.view.order.OrderActivity
import com.otex.homamdriver.view.start.MainActivity
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import java.util.*

@Suppress("DEPRECATION")
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var homeActivityViewModel : HomeActivityViewModel? = null
    var type:String=""
    var status:String="0"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        setuptoolbar()
        click()
    }

    override fun onResume() {
        super.onResume()


        getHomeDashBord()


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

        binding.drawer.updatePassword.setOnClickListener {
            val intent= Intent(this, UpdatePassowrdActivity::class.java)
            startActivity(intent)
        }

        binding.drawer.openCloseBtn.setOnClickListener {
            changeStatus(status)
        }



        binding.waitingbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","pending")
            startActivity(intent)

        }

        binding.deliveredbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","completed")
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

        binding.ondeliverybtn.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            intent.putExtra("type","on_delivery")
            startActivity(intent)
        }


//        binding.readyForbtn.setOnClickListener {
//            val intent=Intent(this,OrderActivity::class.java)
//            intent.putExtra("type","ready_for_delivery")
//            startActivity(intent)
//        }


        binding.drawer.logout.setOnClickListener {
            PrefsUtil.with(this).add("token","").apply()
            val intent =Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun changeStatus(status: String) {

        val map = HashMap<String, String?>()
        map.put("status",status)
        homeActivityViewModel?.openClose(this,map)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun initialize() {
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
            observerHomeRestDashBord()

        getProfile()


    }

    private fun getProfile() {
        homeActivityViewModel?.getProfile(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun observerHomeRestDashBord() {
        homeActivityViewModel!!.homeRestaurantLivedata.observe(this) {

            binding.orderCount.text=it.pending.toString() +" "+getString(R.string.order)
            binding.deliveredCount.text=it.delivered.toString()+" "+getString(R.string.order)
            binding.canceledCount.text=it.canceled.toString() +" "+getString(R.string.order)
            binding.accptedCount.text=it.accepted.toString() +" "+getString(R.string.order)
            binding.totalStoreCount.text=it.revenue.toString() +" "+getString(R.string.order)
            binding.orderWorkOnCount.text=it.working_on.toString() +" "+getString(R.string.order)
            binding.ondeliveryCount.text=it.on_delivery.toString() +" "+getString(R.string.order)


        }

        homeActivityViewModel!!.profileLivedata.observe(this) {

            if (it.restaurant.is_open==0){
                binding.drawer.openCloseBtn.setTextColor(getColor(R.color.green))
                binding.drawer.openCloseBtn.text=getString(R.string.open)
                status="1"
            }else{
                binding.drawer.openCloseBtn.setTextColor(getColor(R.color.red))
                binding.drawer.openCloseBtn.text=getString(R.string.close)
                status="0"
            }

        }

        homeActivityViewModel!!.opencloseLivedata.observe(this) {

            if (it.status==1){
                if (status.equals("0")){
                    Toast.makeText(this,getString(R.string.res_closed), Toast.LENGTH_SHORT).show()
                   binding.drawer.openCloseBtn.setTextColor(getColor(R.color.green))
                   binding.drawer.openCloseBtn.text=getString(R.string.open)
                   status="1"
               }else{
               Toast.makeText(this,getString(R.string.stayss_changes), Toast.LENGTH_SHORT).show()

                   binding.drawer.openCloseBtn.setTextColor(getColor(R.color.red))
                   binding.drawer.openCloseBtn.text=getString(R.string.close)
                   status="0"
               }
            }

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