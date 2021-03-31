package com.otex.homamdriver.view.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.retrofit.HandelCalls
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.login.modeldriver.ModelLoginDriver
import com.otex.homamrestaurant.view.login.model.ModelLoginRestaurant
import java.util.HashMap

class LoginActivityViewModel:ViewModel(),HandleRetrofitResp{

    var loginDriverLivedata = MutableLiveData<ModelLoginDriver>()
    var loginRestaurantLivedata = MutableLiveData<ModelLoginRestaurant>()


    fun makeLoginDriver(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.loginDriver.name, meMap, true, this)

    }

    fun makeLoginRestaurant(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.loginRestaurant.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.loginDriver.name){
            val modelLogin: ModelLoginDriver = o as ModelLoginDriver
            loginDriverLivedata.value = modelLogin
        }else if(flag==DataEnum.loginRestaurant.name){
            val modelLoginRestaurant: ModelLoginRestaurant = o as ModelLoginRestaurant
            loginRestaurantLivedata.value = modelLoginRestaurant
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