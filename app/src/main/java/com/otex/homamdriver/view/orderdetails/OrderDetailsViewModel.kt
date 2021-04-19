package com.otex.homamdriver.view.orderdetails

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.retrofit.HandelCalls
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.orderdetails.model.ModelOrderDetails
import com.otex.homamdriver.view.orderdetails.modelaccept.ModelAccept
import java.util.HashMap

class OrderDetailsViewModel : ViewModel() , HandleRetrofitResp {


    var orderDetailslivedata = MutableLiveData<ModelOrderDetails>()
    var confirmOrderDriverlivedata = MutableLiveData<ModelOrderDetails>()
    var confirmOrderRestlivedata = MutableLiveData<ModelAccept>()

    fun getorderdetails(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.orderdetails.name, meMap, true, this)

    }
    fun confirmOrderDriver(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.confirmdriver.name, meMap, true, this)

    }
    fun confirmOrderRestaurant(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.confirmrest.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag== DataEnum.orderdetails.name){
            val modelOrderDetails: ModelOrderDetails = o as ModelOrderDetails
            orderDetailslivedata.postValue(modelOrderDetails)

        }else if(flag==DataEnum.confirmdriver.name){
            val modelOrderDetails: ModelOrderDetails = o as ModelOrderDetails
            confirmOrderDriverlivedata.postValue(modelOrderDetails)

        }else if(flag==DataEnum.confirmrest.name){
            val modelAccept: ModelAccept = o as ModelAccept
            confirmOrderRestlivedata.postValue(modelAccept)

        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }
}