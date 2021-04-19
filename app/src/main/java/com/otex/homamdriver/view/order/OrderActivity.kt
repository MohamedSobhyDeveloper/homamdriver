package com.otex.homamdriver.view.order

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityOrderBinding
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamdriver.view.order.adapter.MyOrderListAdapter
import java.util.HashMap

class OrderActivity : BaseActivity() {
    lateinit var binding: ActivityOrderBinding
    private var orderActivityViewModel : OrderActivityViewModel? = null
    var status:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        click()

        getOrders()

    }

    private fun getOrders() {
         val map = HashMap<String, String?>()
         map.put("type", PrefsUtil.with(this).get("type","")!!)
         map.put("status",status)
        orderActivityViewModel?.getOrders(this,map)

    }

    private fun click() {

        binding.backbtn.setOnClickListener {

            finish()

        }
    }

    private fun initialize() {

        status=intent.getStringExtra("type").toString()


        if (status.equals("pending")){
            binding.orderTypeTv.text=getString(R.string.waiting_order)
        }else if (status.equals("delivered")){
            binding.orderTypeTv.text=getString(R.string.delivered)

        }else if (status.equals("accepted")){
            binding.orderTypeTv.text=getString(R.string.accepted_order)

        }else if (status.equals("canceled")){
            binding.orderTypeTv.text=getString(R.string.canceled_order)

        }else{
            Log.e("type","failur")
        }


        orderActivityViewModel = ViewModelProvider(this).get(OrderActivityViewModel::class.java)


        orderActivityViewModel!!.myOrderViewModel.observe(this, Observer {
            if (it.data.isNotEmpty()){
                binding.noOrder.visibility= View.GONE

                val layoutManager = LinearLayoutManager(this)
                binding.recOrder.layoutManager = layoutManager
                val adapter =
                    MyOrderListAdapter(this,it.data, status)
                binding.recOrder.adapter = adapter
            }else{
                binding.noOrder.visibility= View.VISIBLE
            }
        })


    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}