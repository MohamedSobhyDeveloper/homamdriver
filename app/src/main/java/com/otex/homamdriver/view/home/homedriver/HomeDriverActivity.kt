package com.otex.homamdriver.view.home.homedriver

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.databinding.ActivityHomeDeliveryBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.view.home.HomeActivityViewModel
import com.otex.homamdriver.view.order.OrderActivity
import com.otex.homamuser.utlitites.PrefsUtil

class HomeDriverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeDeliveryBinding
    private var homeActivityViewModel: HomeActivityViewModel? = null
    var type: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initialize()
        getHomeDashBord()

        click()
    }

    private fun getHomeDashBord() {

        type= PrefsUtil.with(this).get("type","")!!

            homeActivityViewModel!!.getHomeDriverDashbord(this)

    }


    private fun click() {


        binding.waitingbtn.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            intent.putExtra("type","pending")
            startActivity(intent)

        }

        binding.deliveredbtn.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            intent.putExtra("type","delivered")
            startActivity(intent)
        }

        binding.acceptedbtn.setOnClickListener {
//            val intent= Intent(this, OrderActivity::class.java)
//            intent.putExtra("type","accepted")
//            startActivity(intent)
        }

        binding.ondeliverybtn.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            intent.putExtra("type","on_delivery")
            startActivity(intent)
        }

        binding.totalstoreubtn.setOnClickListener {
//            val intent=Intent(this,OrderActivity::class.java)
//            intent.putExtra("type","canceled")
//            startActivity(intent)
        }

        binding.totaldeliveryubtn.setOnClickListener {
//            val intent=Intent(this,OrderActivity::class.java)
//            intent.putExtra("type","canceled")
//            startActivity(intent)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initialize() {
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        observerHomeDriverDashBord()

    }

    @SuppressLint("SetTextI18n")
    private fun observerHomeDriverDashBord() {
        homeActivityViewModel!!.homeDriverLivedata.observe(this) {

            binding.deliveredCount.text=it.delivered.toString() +" "+getString(R.string.order)
           // binding.orderCount.text=it.pending.toString() +" "+getString(R.string.order)
            binding.ondeliveryCount.text=it.on_delivery.toString() +" "+getString(R.string.order)
            binding.totalDeliveryCount.text=it.revenue.toString() +" "+getString(R.string.order)


        }
    }

}