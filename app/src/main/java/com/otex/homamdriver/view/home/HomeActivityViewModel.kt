package com.otex.homamdriver.view.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.retrofit.HandelCalls
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.home.modeldriver.ModelHomeDashBordDriver
import com.otex.homamdriver.view.home.modelprofile.ModelProfile
import com.otex.homamdriver.view.home.modelrestaurant.ModelHomeDashBordRestaurant
import com.otex.homamdriver.view.home.modelstatsu.ModelStatus
import java.util.HashMap

class HomeActivityViewModel:ViewModel(),HandleRetrofitResp{

    var homeDriverLivedata = MutableLiveData<ModelHomeDashBordDriver>()
    var homeRestaurantLivedata = MutableLiveData<ModelHomeDashBordRestaurant>()
    var profileLivedata = MutableLiveData<ModelProfile>()
    var opencloseLivedata = MutableLiveData<ModelStatus>()


    fun getHomeDriverDashbord(context: Context){

        HandelCalls.getInstance(context)?.call(DataEnum.homeDriver.name, null, true, this)

    }
    fun getHomeRestaurantDashbord(context: Context){

        HandelCalls.getInstance(context)?.call(DataEnum.homeRestaurant.name, null, true, this)

    }

    fun getProfile(context: Context){

        HandelCalls.getInstance(context)?.call(DataEnum.profile.name, null, true, this)

    }

    fun openClose(context: Context,meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.openClose.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.homeDriver.name){
            val modelHomeDashBord: ModelHomeDashBordDriver = o as ModelHomeDashBordDriver
            homeDriverLivedata.value = modelHomeDashBord
        }else if(flag==DataEnum.homeRestaurant.name){
            val modelHomeDashBordRestaurant: ModelHomeDashBordRestaurant = o as ModelHomeDashBordRestaurant
            homeRestaurantLivedata.value = modelHomeDashBordRestaurant
        }else if(flag==DataEnum.profile.name){
            val modelProfile: ModelProfile = o as ModelProfile
            profileLivedata.value = modelProfile
        }else if(flag==DataEnum.openClose.name){
            val modelStatus: ModelStatus = o as ModelStatus
            opencloseLivedata.value = modelStatus
        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }

}