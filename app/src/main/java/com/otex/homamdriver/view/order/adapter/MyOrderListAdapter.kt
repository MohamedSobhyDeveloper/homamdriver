package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ItemMyorderBinding
import com.otex.homamdriver.view.orderdetails.OrderDetailsActivity



class MyOrderListAdapter(private val context: Context, val chartList: MutableList<String>?,val type:String)
    : RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder>() {

    var itemBinding: ItemMyorderBinding?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding = ItemMyorderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return MyViewHolder(itemBinding!!)


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        itemBinding?.orderCard?.setOnClickListener {
           context.startActivity(Intent(context, OrderDetailsActivity::class.java))
        }

        if (type.equals("waiting")){
            itemBinding?.orderStatus!!.text=context.getString(R.string.waiting_order)
            itemBinding!!.orderStatus.setTextColor(context.getColor(R.color.waitcolor))
        }else if (type.equals("delivered")){
            itemBinding?.orderStatus!!.text=context.getString(R.string.delivered)
            itemBinding!!.orderStatus.setTextColor(context.getColor(R.color.delivercolor))
        }else if (type.equals("accepted")){
            itemBinding?.orderStatus!!.text=context.getString(R.string.accepted_order)
            itemBinding!!.orderStatus.setTextColor(context.getColor(R.color.acceptedcolor))
        }else if (type.equals("canceled")){
            itemBinding?.orderStatus!!.text=context.getString(R.string.canceled_order)
            itemBinding!!.orderStatus.setTextColor(context.getColor(R.color.cancelcolor))
        }




    }



    fun addList(movielist: MutableList<String>) {

        this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {
        if (chartList?.size== null) {
            return 10
        } else {
            return chartList.size
        }
    }



    class MyViewHolder(itemBinding: ItemMyorderBinding) : RecyclerView.ViewHolder(itemBinding.root)
}