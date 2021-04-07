package com.otex.homamdriver.view.order.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.ItemMyorderBinding
import com.otex.homamdriver.view.order.model.Data
import com.otex.homamdriver.view.orderdetails.OrderDetailsActivity
import com.squareup.picasso.Picasso


class MyOrderListAdapter(private val context: Context, val list: List<Data>,
                         val status:String)
    : RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder>() {




    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (status.equals("pending")){
            holder.binding.orderStatus.text=context.getString(R.string.waiting_order)
            holder.binding.orderStatus.setTextColor(context.getColor(R.color.waitcolor))
        }else if (status.equals("delivered")){
            holder.binding.orderStatus.text=context.getString(R.string.delivered)
            holder.binding.orderStatus.setTextColor(context.getColor(R.color.delivercolor))
        }else if (status.equals("accepted")){
            holder.binding.orderStatus.text=context.getString(R.string.accepted_order)
            holder.binding.orderStatus.setTextColor(context.getColor(R.color.acceptedcolor))
        }else if (status.equals("canceled")){
            holder.binding.orderStatus.text=context.getString(R.string.canceled_order)
            holder.binding.orderStatus.setTextColor(context.getColor(R.color.cancelcolor))
        }

        holder.binding.orderName.text=list[position].restaurant
        holder.binding.numOrder.text="#"+list[position].code
        holder.binding.txtDate.text=list[position].date
        holder.binding.numItem.text=list[position].dishes.toString()+" "+context.getString(R.string.dished)
        holder.binding.costTv.text=list[position].total.toString()+" "+context.getString(R.string.le)
        Picasso.get().load(list[position].restaurant_logo).into(holder.binding.imgOrder)


        holder.binding.orderCard.setOnClickListener {
            val intent=Intent(context,OrderDetailsActivity::class.java)
            intent.putExtra("status",status)
            intent.putExtra("order_id",list[position].id.toString())
            context.startActivity(intent)

        }






    }



    fun addList(list: MutableList<Data>) {

        this.list.toMutableList().addAll(list)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return list.size

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_myorder, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMyorderBinding.bind(view)
    }

}