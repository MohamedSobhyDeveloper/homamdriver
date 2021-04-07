package com.otex.homamdriver.view.orderdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityOrderDetailsBinding

import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.OrderDetailsAdapter
import com.squareup.picasso.Picasso
import java.util.HashMap

class OrderDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityOrderDetailsBinding
    private var orderDetailsViewModel : OrderDetailsViewModel? = null
    var status:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
          typeorder()
          initialize()
          click()


    }

    private fun click() {
        binding.backbtn.setOnClickListener {

            finish()
        }
    }

    @SuppressLint("NewApi")
    private fun typeorder() {
        status= intent.getStringExtra("status").toString()

        if(status.equals("pending")){
            binding.txtType.text=getString(R.string.waiting_order)
            binding.txtType.visibility=View.GONE
            binding.acceptRejectOrder.visibility=View.VISIBLE
            binding.txtType.setTextColor(getColor(R.color.waitcolor))
        }else if(status.equals("delivered")){
            binding.txtType.text=getString(R.string.delivered)
            binding.txtType.setTextColor(getColor(R.color.delivercolor))
        }else if(status.equals("accepted")){
            binding.txtType.text=getString(R.string.accepted_order)
            binding.txtType.setTextColor(getColor(R.color.acceptedcolor))
        }else if(status.equals("canceled")){
            binding.txtType.text=getString(R.string.canceled_order)
            binding.txtType.setTextColor(getColor(R.color.cancelcolor))
        }



    }
    private fun initialize() {
        orderDetailsViewModel = ViewModelProvider(this).get(OrderDetailsViewModel::class.java)
        orderDetailsViewModel!!.orderDetailslivedata.observe(this) {

            Picasso.get().load(it.data.restaurant_logo).into(binding.imgRest)
            binding.resName.text=it.data.restaurant
            binding.txtPriceTotalEnd.text=it.data.total.toString()
            binding.txtPriceDelivery.text=it.data.shipping_fees.toString()
            binding.txtPriceTotalFirst.text=(it.data.total-it.data.shipping_fees).toString()

            val layoutManager = LinearLayoutManager(this)
            binding.recOrderCart.layoutManager = layoutManager
            val adapter =
                OrderDetailsAdapter(this,it.data.items)
            binding.recOrderCart.adapter = adapter

        }


        val map = HashMap<String, String?>()
        map.put("type", PrefsUtil.with(this).get("type","")!!)
        map.put("order_id",intent.getStringExtra("order_id"))
        orderDetailsViewModel?.getorderdetails(this,map)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}