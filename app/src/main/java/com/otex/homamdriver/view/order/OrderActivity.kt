package com.otex.homamdriver.view.order

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ActivityOrderBinding
import com.otex.homamdriver.view.order.adapter.MyOrderListAdapter
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import java.util.*

class OrderActivity : BaseActivity() {
    lateinit var binding: ActivityOrderBinding
    private var orderActivityViewModel : OrderActivityViewModel? = null
    var status:String=""
    private var loading = true
    private var pastVisiblesItems = 0
    private  var visibleItemCount:Int = 0
    private  var totalItemCount:Int = 0
    var layoutManager = LinearLayoutManager(this)
    private var nextPage = ""
    var adapter:MyOrderListAdapter?=null

    private var resume = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        click()

        getOrders()



    }

    override fun onResume() {
        super.onResume()

        if (resume){
            if (status.equals("pending")||status.equals("accepted")||status.equals("working_on")||status.equals("on_delivery")){
                getOrders()
            }
        }

        resume=true




    }

    private fun getOrders() {
         val map = HashMap<String, String?>()
         map.put("type", PrefsUtil.with(this).get("type", "")!!)
         map.put("status", status)
         orderActivityViewModel?.getOrders(this, map)

    }

    private fun click() {

        binding.backbtn.setOnClickListener {

            finish()

        }
    }

    private fun initialize() {


        binding.recOrder.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount()
                    totalItemCount = layoutManager.getItemCount()
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            CallOrderMore()
                            loading = false
                        }
                    }
                }
            }
        })




        status=intent.getStringExtra("type").toString()


        if (status.equals("pending")){
            binding.orderTypeTv.text=getString(R.string.waiting_order)
        }else if (status.equals("completed")){
            binding.orderTypeTv.text=getString(R.string.delivered)

        }else if (status.equals("accepted")){
            binding.orderTypeTv.text=getString(R.string.accepted_order)

        }else if (status.equals("canceled")){
            binding.orderTypeTv.text=getString(R.string.canceled_order)

        }else if (status.equals("working_on")){
            binding.orderTypeTv.text=getString(R.string.working_on)

        }else if (status.equals("ready_for_delivery")){
            binding.orderTypeTv.text=getString(R.string.ready_for_delivery)

        }else if (status.equals("on_delivery")){
            binding.orderTypeTv.text=getString(R.string.on_delivery)

        }


        orderActivityViewModel = ViewModelProvider(this).get(OrderActivityViewModel::class.java)


        orderActivityViewModel!!.myOrderViewModel.observe(this, Observer {
            if (it.data.isNotEmpty()) {
                binding.noOrder.visibility = View.GONE
                binding.recOrder.visibility=View.VISIBLE

                binding.recOrder.layoutManager = layoutManager
                 adapter =
                    MyOrderListAdapter(this, it.data, status)
                binding.recOrder.adapter = adapter
            } else {
                binding.recOrder.visibility=View.GONE
                binding.noOrder.visibility = View.VISIBLE
            }
        })

        orderActivityViewModel!!.urlPaginationLiveData.observe(this) {

            adapter?.addList(it.data)

            if(it.next_page_url !=null && it.next_page_url.isNotEmpty() ){

                loading=true
                nextPage=it.next_page_url

            }

        }


    }

    private fun CallOrderMore() {
        val map = HashMap<String, String?>()
        map.put("url",nextPage)
        orderActivityViewModel?.getUrlPagination(this,map)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}