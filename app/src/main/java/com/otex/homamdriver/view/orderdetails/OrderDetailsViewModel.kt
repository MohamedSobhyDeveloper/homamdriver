package com.otex.homamdriver.view.orderdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderDetailsViewModel : ViewModel() {


    var orderDetailslivedata = MutableLiveData<String>()

    fun getorderdetails(){

        orderDetailslivedata.postValue("Egypt")
    }
}