package com.otex.homamdriver.view.orderdetails

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.retrofit.HandelCalls
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.orderdetails.model.ModelOrderDetails
import java.util.HashMap

class OrderDetailsViewModel : ViewModel() , HandleRetrofitResp {


    var orderDetailslivedata = MutableLiveData<ModelOrderDetails>()

    fun getorderdetails(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.orderdetails.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag== DataEnum.orderdetails.name){
            val modelOrderDetails: ModelOrderDetails = o as ModelOrderDetails
            orderDetailslivedata.postValue(modelOrderDetails)

        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }
}