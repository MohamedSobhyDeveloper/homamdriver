package com.otex.homamdriver.view.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.view.order.OrderActivity
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity

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

        click()
    }

    private fun getHomeDashBord() {

        type= PrefsUtil.with(this).get("type","")!!
        if(type==Constant.store){
            binding.totalstoreubtn.visibility=View.VISIBLE
            binding.acceptedbtn.visibility=View.VISIBLE
            binding.totaldeliveryubtn.visibility=View.GONE

            homeActivityViewModel!!.getHomeRestaurantDashbord(this)

        }else{
            binding.totalstoreubtn.visibility=View.GONE
            binding.acceptedbtn.visibility=View.GONE
            binding.totaldeliveryubtn.visibility=View.VISIBLE
            homeActivityViewModel!!.getHomeDriverDashbord(this)

        }


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


        }
    }

    @SuppressLint("SetTextI18n")
    private fun observerHomeDriverDashBord() {
        homeActivityViewModel!!.homeDriverLivedata.observe(this) {

            binding.deliveredCount.text=it.delivered.toString() +" "+getString(R.string.order)
            binding.orderCount.text=it.pending.toString() +" "+getString(R.string.order)
            binding.canceledCount.text=it.canceled.toString() +" "+getString(R.string.order)
            binding.totalDeliveryCount.text=it.revenue.toString() +" "+getString(R.string.order)


        }
    }
}