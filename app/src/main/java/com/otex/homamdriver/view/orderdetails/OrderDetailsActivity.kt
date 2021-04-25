package com.otex.homamdriver.view.orderdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityOrderDetailsBinding
import com.otex.homamdriver.utlitites.Constant

import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.OrderDetailsAdapter
import com.squareup.picasso.Picasso
import es.dmoral.toasty.Toasty
import java.util.HashMap

class OrderDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityOrderDetailsBinding
    private var orderDetailsViewModel : OrderDetailsViewModel? = null
    var status:String=""
    var type:String=""
    var click:String=""

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

        binding.btnAccepted.setOnClickListener {

            acceptOrder(type,"1")

        }

        binding.btnRejected.setOnClickListener {
            acceptOrder(type,"0")

        }

        binding.btnWorkon.setOnClickListener {
            click="working_on"
            val map = HashMap<String, String?>()
            map.put("status","working_on")
            map.put("order_id",intent.getStringExtra("order_id"))
            orderDetailsViewModel?.changeStatusRestaurant(this,map)
        }

        binding.btnReadydelivery.setOnClickListener {
            click="ready_for_delivery"
            val map = HashMap<String, String?>()
            map.put("status","ready_for_delivery")
            map.put("order_id",intent.getStringExtra("order_id"))
            orderDetailsViewModel?.changeStatusRestaurant(this,map)
        }

        binding.btnDelivered.setOnClickListener {
            val map = HashMap<String, String?>()
            map.put("order_id",intent.getStringExtra("order_id"))
            orderDetailsViewModel?.confirmOrderDriver(this,map)
        }
    }

    private fun acceptOrder(type: String, s: String) {

        if(type==Constant.store){
            val map = HashMap<String, String?>()
            map.put("status",s)
            map.put("order_id",intent.getStringExtra("order_id"))
            orderDetailsViewModel?.confirmOrderRestaurant(this,map)
        }else{
            val map = HashMap<String, String?>()
            map.put("order_id",intent.getStringExtra("order_id"))
            orderDetailsViewModel?.pickOrderDriver(this,map)
        }

    }

    @SuppressLint("NewApi")
    private fun typeorder() {
        status= intent.getStringExtra("status").toString()
        type= PrefsUtil.with(this).get("type","")!!


        if(status.equals("pending")){
            confirm_Or_Reject(type,status)
            binding.txtType.text=getString(R.string.waiting_order)
            binding.txtType.visibility=View.GONE
            binding.acceptRejectOrder.visibility=View.VISIBLE
            binding.txtType.setTextColor(getColor(R.color.waitcolor))
        }else if(status.equals("completed")){
            binding.txtType.text=getString(R.string.delivered)
            binding.txtType.setTextColor(getColor(R.color.delivercolor))
        }else if(status.equals("accepted")){
            confirm_Or_Reject(type,status)
            binding.txtType.text=getString(R.string.accepted_order)
            binding.txtType.setTextColor(getColor(R.color.acceptedcolor))
        }else if(status.equals("canceled")){
            binding.txtType.text=getString(R.string.canceled_order)
            binding.txtType.setTextColor(getColor(R.color.cancelcolor))
        }else if (status.equals("working_on")){
            confirm_Or_Reject(type,status)
            binding.txtType.text=getString(R.string.working_on)
            binding.txtType.setTextColor(getColor(R.color.acceptedcolor))

        }else if (status.equals("ready_for_delivery")){
            confirm_Or_Reject(type,status)

            binding.txtType.text=getString(R.string.ready_for_delivery)
            binding.txtType.setTextColor(getColor(R.color.acceptedcolor))

        }else if (status.equals("on_delivery")){
            confirm_Or_Reject(type,status)

            binding.txtType.text=getString(R.string.on_delivery)
            binding.txtType.setTextColor(getColor(R.color.acceptedcolor))

        }



    }

    private fun confirm_Or_Reject(type: String,status:String) {

        if(status.equals("pending")){

            if(type==Constant.driver){
                binding.btnAccepted.visibility=View.VISIBLE
                binding.btnRejected.visibility=View.GONE
            }else{
                binding.btnAccepted.visibility=View.VISIBLE
                binding.btnRejected.visibility=View.VISIBLE
            }
        }else if(status.equals("accepted")){
            binding.btnWorkon.visibility=View.VISIBLE
        }else if(status.equals("working_on")){
            binding.btnReadydelivery.visibility=View.VISIBLE
       }else if(status.equals("on_delivery")){
            binding.btnDelivered.visibility=View.VISIBLE
            }else if(status.equals("ready_for_delivery")){
            if(type==Constant.driver){
                binding.btnAccepted.visibility=View.VISIBLE
                binding.btnRejected.visibility=View.GONE
            }

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

        orderDetailsViewModel!!.confirmOrderDriverlivedata.observe(this) {

            if (it.status==1){
                Toasty.success(this, getString(R.string.order_delivered), Toast.LENGTH_SHORT, true).show()
                finish()
            }

        }

        orderDetailsViewModel!!.pickOrderDriverlivedata.observe(this) {

            if (it.status==1){
                Toasty.success(this, getString(R.string.order_picked), Toast.LENGTH_SHORT, true).show()
                finish()
            }

        }
        orderDetailsViewModel!!.confirmOrderRestlivedata.observe(this) {

            if(it.status==1){
                if (it.order.order_status.equals("1")){
                   Toasty.success(this, getString(R.string.ordercofirm), Toast.LENGTH_SHORT, true).show()

                }else{
                   Toasty.success(this, getString(R.string.order_canceled), Toast.LENGTH_SHORT, true).show()
                }
                finish()
            }


        }


        orderDetailsViewModel!!.changeStatusLiveData.observe(this) {

            if(it.status==1){
                if (click.equals("working_on")){
                    Toasty.success(this, getString(R.string.start_work_on), Toast.LENGTH_SHORT, true).show()

                }else{
                    Toasty.success(this, getString(R.string.ready_for_delivery), Toast.LENGTH_SHORT, true).show()
                }
                finish()
            }


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