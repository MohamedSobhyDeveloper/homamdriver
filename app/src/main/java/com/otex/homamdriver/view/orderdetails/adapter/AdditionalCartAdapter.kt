package com.otex.homamdriver.view.orderdetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamdriver.R
import com.otex.homamdriver.databinding.AdditionalItemCartBinding
import com.otex.homamdriver.view.orderdetails.model.Detail


class AdditionalCartAdapter(private val context: Context, val mList: List<Detail>)
    : RecyclerView.Adapter<AdditionalCartAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        holder.binding.txtCompo.text=mList[position].name
        holder.binding.txtPriceCompo.text= mList[position].price.toString()

    }



    fun addList(movielist: MutableList<Detail>) {

       // this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return mList.size

    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.additional_item_cart, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = AdditionalItemCartBinding.bind(view)
    }
}