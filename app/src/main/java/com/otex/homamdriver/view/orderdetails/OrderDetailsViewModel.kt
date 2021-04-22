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
    var confirmOrderDriverlivedata = MutableLiveData<ModelAccept>()
    var pickOrderDriverlivedata = MutableLiveData<ModelAccept>()
    var confirmOrderRestlivedata = MutableLiveData<ModelAccept>()
    var changeStatusLiveData = MutableLiveData<ModelAccept>()

    fun getorderdetails(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.orderdetails.name, meMap, true, this)

    }

    fun confirmOrderRestaurant(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.confirmrest.name, meMap, true, this)

    }

    fun changeStatusRestaurant(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.changeStatus.name, meMap, true, this)

    }

    fun pickOrderDriver(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.pickOrder.name, meMap, true, this)

    }

    fun confirmOrderDriver(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.confirmdriver.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag== DataEnum.orderdetails.name){
            val modelOrderDetails: ModelOrderDetails = o as ModelOrderDetails
            orderDetailslivedata.postValue(modelOrderDetails)

        }else if(flag==DataEnum.confirmdriver.name){
            val modelAccept: ModelAccept = o as ModelAccept
            confirmOrderDriverlivedata.postValue(modelAccept)

        }else if(flag==DataEnum.pickOrder.name){
            val modelAccept: ModelAccept = o as ModelAccept
            pickOrderDriverlivedata.postValue(modelAccept)

        }else if(flag==DataEnum.confirmrest.name){
            val modelAccept: ModelAccept = o as ModelAccept
            confirmOrderRestlivedata.postValue(modelAccept)

        }else if(flag==DataEnum.changeStatus.name){
            val modelAccept: ModelAccept = o as ModelAccept
            changeStatusLiveData.postValue(modelAccept)

        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }
}