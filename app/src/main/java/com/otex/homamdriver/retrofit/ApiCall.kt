package com.otex.homamdriver.retrofit

import com.otex.homamdriver.view.home.modeldriver.ModelHomeDashBordDriver
import com.otex.homamdriver.view.home.modelrestaurant.ModelHomeDashBordRestaurant
import com.otex.homamdriver.view.login.modeldriver.ModelLoginDriver
import com.otex.homamdriver.view.order.model.ModelOrder
import com.otex.homamdriver.view.orderdetails.model.ModelOrderDetails
import com.otex.homamrestaurant.view.login.model.ModelLoginRestaurant
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap


interface ApiCall {

    @GET("services/rest/?")
    fun getPhotos(@QueryMap requestBody: HashMap<String, String?>?): Call<String?>?

    @FormUrlEncoded
    @POST("driver/login")
    fun loginDriver(@FieldMap map: HashMap<String, String?>?): Call<ModelLoginDriver?>?

    @GET("driver/dashboard")
    fun homeDashBordDriver():Call<ModelHomeDashBordDriver?>?

    @FormUrlEncoded
    @POST("restaurant/login")
    fun loginRestaurant(@FieldMap map: HashMap<String, String?>?): Call<ModelLoginRestaurant?>?

    @GET("restaurant/dashboard")
    fun homeDashBordRestaurant():Call<ModelHomeDashBordRestaurant?>?

    @GET("{type}/orders/status/{status}")
    fun getOrder(@Path("status") status:String,@Path("type") type:String): Call<ModelOrder?>?

    @GET("{type}/orders/{order_id}")
    fun getOrderDetails(@Path("order_id") order_id:String,@Path("type") type:String): Call<ModelOrderDetails?>?


}