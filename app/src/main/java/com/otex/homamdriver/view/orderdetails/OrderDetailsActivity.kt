package com.otex.homamdriver.view.orderdetails

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityOrderDetailsBinding
import com.otex.homamdriver.view.login.LoginActivityViewModel
import com.otex.homamdriver.view.order.OrderActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.MyOrderListAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.OrderDetailsAdapter

class OrderDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderDetailsBinding
    private var orderDetailsViewModel : OrderDetailsViewModel? = null
    var type:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
          typeorder()
          initialize()



    }
    @SuppressLint("NewApi")
    private fun typeorder() {
         type= intent.getStringExtra("type").toString()

        if(type.equals("waiting")){
            binding.txtType.text=getString(R.string.waiting_order)
            binding.txtType.setTextColor(getColor(R.color.waitcolor))
        }else if(type.equals("delivered")){
            binding.txtType.text=getString(R.string.delivered)
            binding.txtType.setTextColor(getColor(R.color.delivercolor))
        }else if(type.equals("accepted")){
            binding.txtType.text=getString(R.string.accepted_order)
            binding.txtType.setTextColor(getColor(R.color.acceptedcolor))
        }

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this,OrderActivity::class.java))
        }

    }
    private fun initialize() {
        orderDetailsViewModel = ViewModelProvider(this).get(OrderDetailsViewModel::class.java)
        orderDetailsViewModel!!.getorderdetails()
        orderDetailsViewModel!!.orderDetailslivedata.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recOrderCart.layoutManager = layoutManager
            val adapter =
                OrderDetailsAdapter(this,null)
            binding.recOrderCart.adapter = adapter

        }
    }

}