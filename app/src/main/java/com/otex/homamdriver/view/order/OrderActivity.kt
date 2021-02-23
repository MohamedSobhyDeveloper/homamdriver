package com.otex.homamdriver.view.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityOrderBinding
import com.otex.homamdriver.view.home.HomeActivity
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.MyOrderListAdapter

class OrderActivity : BaseActivity() {
    lateinit var binding: ActivityOrderBinding
    private var orderActivityViewModel : OrderActivityViewModel? = null
    var type:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        click()

        getOrders()

    }

    private fun getOrders() {

        orderActivityViewModel?.getOrders()

    }

    private fun click() {

        binding.backbtn.setOnClickListener {

            finish()

        }
    }

    private fun initialize() {

         type=intent.getStringExtra("type").toString()

        if (type.equals("waiting")){
            binding.orderTypeTv.text=getString(R.string.waiting_order)
        }else if (type.equals("delivered")){
            binding.orderTypeTv.text=getString(R.string.delivered)

        }else if (type.equals("accepted")){
            binding.orderTypeTv.text=getString(R.string.accepted_order)

        }else if (type.equals("canceled")){
            binding.orderTypeTv.text=getString(R.string.canceled_order)

        }else{
            Log.e("type","failur")
        }


        orderActivityViewModel = ViewModelProvider(this).get(OrderActivityViewModel::class.java)

        orderActivityViewModel!!.myOrderViewModel.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recOrder.layoutManager = layoutManager
            val adapter =
                MyOrderListAdapter(this,null, type!!)
            binding.recOrder.adapter = adapter

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}