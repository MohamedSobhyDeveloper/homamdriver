package com.otex.homamdriver.view.order

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.retrofit.HandelCalls
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.order.model.ModelOrder
import java.util.HashMap

class OrderActivityViewModel:ViewModel(), HandleRetrofitResp {

    var myOrderViewModel = MutableLiveData<ModelOrder>()

    fun getOrders(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.order.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag== DataEnum.order.name){
            val modelorder: ModelOrder = o as ModelOrder
            myOrderViewModel.postValue(modelorder)

        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {

    }

    override fun onNoContent(flag: String?, code: Int) {

    }

    override fun onBadRequest(flag: String?, o: Any?) {

    }

}