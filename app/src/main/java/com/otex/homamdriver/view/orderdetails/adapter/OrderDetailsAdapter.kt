package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ItemCartBinding
import com.otex.homamdriver.view.orderdetails.adapter.AdditionalCartAdapter
import com.otex.homamdriver.view.orderdetails.model.Item

class OrderDetailsAdapter(private val context: Context, val list: List<Item>)
    : RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.txtNumOrder.text= list[position].quantity.toString()
        holder.binding.txtNameOrder.text=list[position].name
        holder.binding.txtPriceOrder.text= list[position].total_price.toString()

        val layoutManager = LinearLayoutManager(context)
        holder.binding.recAdditionalItem.layoutManager = layoutManager
        val adapter =
            AdditionalCartAdapter(context,list[position].details)
        holder.binding.recAdditionalItem.adapter = adapter


    }



    fun addList(list: MutableList<Item>) {

        this.list.toMutableList().addAll(list)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {
            return list?.size!!

    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCartBinding.bind(view)
    }
}