package com.otex.homamdriver.view.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityHomeBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.view.login.LoginActivityViewModel
import com.otex.homamdriver.view.order.OrderActivity
import com.otex.homamuser.view.baseActivity.BaseActivity

@Suppress("DEPRECATION")
class HomeActivity : BaseActivity() {
    lateinit var binding: ActivityHomeBinding
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

        type=intent.getStringExtra("type").toString()
        if(type==Constant.store){
            binding.totalstoreubtn.visibility=View.VISIBLE
            homeActivityViewModel!!.getHomeRestaurantDashbord(this)

        }else{
            binding.totalstoreubtn.visibility=View.GONE
            homeActivityViewModel!!.getHomeDriverDashbord(this)

        }


    }


    private fun click() {


        binding.waitingbtn.setOnClickListener {
            val intent=Intent(this,OrderActivity::class.java)
            intent.putExtra("type","waiting")
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

    private fun observerHomeRestDashBord() {
        homeActivityViewModel!!.homeRestaurantLivedata.observe(this) {

            binding.orderCount.text=it.pending.toString() +" Order"
            binding.deliveredCount.text=it.delivered.toString() +" Order"
            binding.canceledCount.text=it.canceled.toString() +" Order"
            binding.accptedCount.text=it.accepted.toString() +" Order"
            binding.totalStoreCount.text=it.revenue.toString() +" Order"


        }
    }

    private fun observerHomeDriverDashBord() {
        homeActivityViewModel!!.homeDriverLivedata.observe(this) {

            binding.orderCount.text=it.pending.toString() +" Order"
            binding.deliveredCount.text=it.delivered.toString() +" Order"
            binding.canceledCount.text=it.canceled.toString() +" Order"
            binding.accptedCount.text=it.revenue.toString() +" Order"


        }
    }
}