package com.otex.homamdriver.view.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.retrofit.HandelCalls
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.view.login.model.ModelLogin
import java.util.HashMap

class LoginActivityViewModel:ViewModel(),HandleRetrofitResp{

    var loginLivedata = MutableLiveData<ModelLogin>()


    fun makeLogin(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.login.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.login.name){
            val modelLogin: ModelLogin = o as ModelLogin
            loginLivedata.value = modelLogin
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