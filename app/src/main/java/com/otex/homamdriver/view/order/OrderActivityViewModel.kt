package com.otex.homamdriver.view.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderActivityViewModel:ViewModel() {

    var myOrderViewModel = MutableLiveData<String>()

    fun getOrders(){
        myOrderViewModel.postValue("done")
    }

}