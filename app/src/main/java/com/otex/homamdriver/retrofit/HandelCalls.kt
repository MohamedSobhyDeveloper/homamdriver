package com.otex.homamdriver.retrofit

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.otex.homamdriver.interfaces.HandleRetrofitResp
import com.otex.homamdriver.interfaces.HandleRetrofitRespAdapter
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamdriver.utlitites.HelpMe
import com.otex.homamdriver.utlitites.Loading
import es.dmoral.toasty.Toasty
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

/**
 * Created by lenovo on 1/3/2018.
 */
class HandelCalls {
    private var onRespnse: HandleRetrofitResp? = null
    private var onRespnseAdapter: HandleRetrofitRespAdapter? = null

    /**
     * @param onRespnseSucess
     */
    fun setonRespnseSucess(onRespnseSucess: HandleRetrofitResp?) {
        onRespnse = onRespnseSucess
    }

    fun setonRespnseSucessApapter(onRespnseAdapter: HandleRetrofitRespAdapter?) {
        this.onRespnseAdapter = onRespnseAdapter
    }

    fun call(flag: String, meMap: HashMap<String, String?>?, ShowLoadingDialog: Boolean, onRespnseSucess: HandleRetrofitResp) {
        onRespnse = onRespnseSucess

        if (flag== DataEnum.loginDriver.name){
            callRetrofit(restRetrofit!!.getClientService().loginDriver(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.loginRestaurant.name){
            callRetrofit(restRetrofit!!.getClientService().loginRestaurant(meMap), flag, ShowLoadingDialog)

        } else if(flag==DataEnum.homeDriver.name){

            callRetrofit(restRetrofit!!.getClientService().homeDashBordDriver(), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.homeRestaurant.name){

            callRetrofit(restRetrofit!!.getClientService().homeDashBordRestaurant(), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.order.name){
            val status=meMap?.get("status")
            val type=meMap?.get("type")
            callRetrofit(restRetrofit!!.getClientService().getOrder(status!!, type!!), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.orderdetails.name){
            val order_id=meMap?.get("order_id")
            val type=meMap?.get("type")
            callRetrofit(restRetrofit!!.getClientService().getOrderDetails(order_id!!, type!!), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.confirmdriver.name){
            val order_id=meMap?.get("order_id")
            val type=meMap?.get("type")
            callRetrofit(restRetrofit!!.getClientService().confirmOrderDriver(order_id!!), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.confirmrest.name){
            val order_id=meMap?.get("order_id")
            val status=meMap?.get("status")
            callRetrofit(restRetrofit!!.getClientService().confirmOrderRest(order_id!!, status!!), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.URLpagination.name){
            val url=meMap?.get("url")

            callRetrofit(restRetrofit?.getClientService()?.getURL(url), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.changeStatus.name){
            val order_id=meMap?.get("order_id")
            val status=meMap?.get("status")
            callRetrofit(restRetrofit!!.getClientService().changeStatusRes(order_id!!, status!!), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.pickOrder.name){
            val order_id=meMap?.get("order_id")
            callRetrofit(restRetrofit!!.getClientService().pickOrderDriver(order_id!!), flag, ShowLoadingDialog)
        }else if(flag==DataEnum.updatePass.name){
            val type=meMap?.get("type")
            callRetrofit(restRetrofit!!.getClientService().updatePassword(meMap, type!!), flag, ShowLoadingDialog)
        }else if(flag==DataEnum.openClose.name){
            val status=meMap?.get("status")
            callRetrofit(restRetrofit!!.getClientService().openClose(status!!), flag, ShowLoadingDialog)
        }else if(flag==DataEnum.profile.name){
            callRetrofit(restRetrofit!!.getClientService().profile(), flag, ShowLoadingDialog)
        }



    }



    fun <T> callRetrofit(call: Call<T>?, flag: String?, ShowDialog: Boolean) {
        val progressDialog = Loading(context)
        if (ShowDialog) {
            progressDialog.show()
        }
        call!!.enqueue(object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                Log.d("test", "onResponse() called with: call = [$call], response = [$response]response returned")
                if (ShowDialog) {
                    progressDialog.dismiss()
                }
                Log.e(TAG, "onResponse: $response")
                if (response.code() == 200) {
                    if (response.isSuccessful && response.body() != null) {
                        if (onRespnse != null) Log.d("testing", "onResponse() minma called with: call = [$call], response = [$response]")
                        onRespnse!!.onResponseSuccess(flag, response.body())
                    }
                } else if (response.code() == 400 || response.code() == 401 || response.code() == 300) {
                    Log.e("res1", "resp")
                    if (onRespnse != null) {
                        Log.e("res2", "resp")
                        try {
                            Log.e("res3", "resp")
                            // Log.e("resp",response.errorBody().string());
                            // onRespnse.onBadRequest(flag, response.errorBody().string());
                            // Log.e("resp",response.errorBody().string());
                            val o = JSONObject(response.errorBody()!!.string())
                            if (o.has("message")){
                                val message=o.getString("message")
                                if (message.isNotEmpty()){
                                    Toasty.error(context!!, message, Toast.LENGTH_SHORT, true).show()
                                }
                            }else{
                                if (o.has("error")){
                                    val error=o.getString("error")

                                    if (error.isNotEmpty()){
                                        Toasty.error(context!!, error, Toast.LENGTH_SHORT, true).show()

                                    }
                                }
                            }



//                            onRespnse!!.onBadRequest(flag, response.errorBody()!!.string())

                        } catch (e: IOException) {
                            e.printStackTrace()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                if (ShowDialog) progressDialog.dismiss()
                HelpMe.getInstance(context)!!.retrofironFailure(t)
            }
        })
    }

    companion object {
        /**
         * Created by lenovo on 6/28/2017.
         */
        val TAG = HandelCalls::class.java.simpleName
        private var context: Context? = null
        private var instance: HandelCalls? = null
        private var restRetrofit: RestRetrofit? = null
        //private HandleNoContent onNoContent;
        /**
         * @param context create ana object if it's not already created (singleton)
         * @return reference to that class
         */
        fun getInstance(context: Context?): HandelCalls? {
            Companion.context = context
            if (instance == null) {
                instance = HandelCalls()
                restRetrofit = RestRetrofit.getInstance(context)
            }
            return instance
        }
    }
}