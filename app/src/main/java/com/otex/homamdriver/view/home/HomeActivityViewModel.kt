package com.otex.homamdriver.view.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.retrofit.HandelCalls
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.home.modeldriver.ModelHomeDashBordDriver
import com.otex.homamdriver.view.home.modelrestaurant.ModelHomeDashBordRestaurant

class HomeActivityViewModel:ViewModel(),HandleRetrofitResp{

    var homeDriverLivedata = MutableLiveData<ModelHomeDashBordDriver>()
    var homeRestaurantLivedata = MutableLiveData<ModelHomeDashBordRestaurant>()


    fun getHomeDriverDashbord(context: Context){

        HandelCalls.getInstance(context)?.call(DataEnum.homeDriver.name, null, true, this)

    }
    fun getHomeRestaurantDashbord(context: Context){

        HandelCalls.getInstance(context)?.call(DataEnum.homeRestaurant.name, null, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.homeDriver.name){
            val modelHomeDashBord: ModelHomeDashBordDriver = o as ModelHomeDashBordDriver
            homeDriverLivedata.value = modelHomeDashBord
        }else if(flag==DataEnum.homeRestaurant.name){
            val modelHomeDashBordRestaurant: ModelHomeDashBordRestaurant = o as ModelHomeDashBordRestaurant
            homeRestaurantLivedata.value = modelHomeDashBordRestaurant
        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
        TODO("Not yet implemented")
    }

    override fun onNoContent(flag: String?, code: Int) {
        TODO("Not yet implemented")
    }

    override fun onBadRequest(flag: String?, o: Any?) {
        TODO("Not yet implemented")
    }

}