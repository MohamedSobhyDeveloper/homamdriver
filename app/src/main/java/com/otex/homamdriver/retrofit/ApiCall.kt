package com.otex.homamdriver.retrofit

import com.otex.homamdriver.view.home.modeldriver.ModelHomeDashBordDriver
import com.otex.homamdriver.view.home.modelprofile.ModelProfile
import com.otex.homamdriver.view.home.modelrestaurant.ModelHomeDashBordRestaurant
import com.otex.homamdriver.view.home.modelstatsu.ModelStatus
import com.otex.homamdriver.view.login.modeldriver.ModelLoginDriver
import com.otex.homamdriver.view.login.modelpassword.ModelUpdatePassword
import com.otex.homamdriver.view.order.model.ModelOrder
import com.otex.homamdriver.view.orderdetails.model.ModelOrderDetails
import com.otex.homamdriver.view.orderdetails.modelaccept.ModelAccept
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

    @GET
    fun getURL(@Url url: String?): Call<ModelOrder?>?


    @GET("{type}/orders/{order_id}")
    fun getOrderDetails(@Path("order_id") order_id:String,@Path("type") type:String): Call<ModelOrderDetails?>?


    @POST("driver/confirm-order/{order_id}")
    fun confirmOrderDriver(@Path("order_id") order_id:String): Call<ModelAccept?>?

    @POST("driver/pick-order/{order_id}")
    fun pickOrderDriver(@Path("order_id") order_id:String): Call<ModelAccept?>?

    @POST("restaurant/orders/{order_id}/confirm-order/{status}")
    fun confirmOrderRest(@Path("order_id") order_id:String,@Path("status") status:String): Call<ModelAccept?>?


    @POST("restaurant/orders/{order_id}/change-status/{status}")
    fun changeStatusRes(@Path("order_id") order_id:String,@Path("status") status:String): Call<ModelAccept?>?


    @FormUrlEncoded
    @POST("{type}/update-password")
    fun updatePassword(@FieldMap map: HashMap<String, String?>?,@Path("type") type:String): Call<ModelUpdatePassword?>?


    @GET("restaurant/profile")
    fun profile(): Call<ModelProfile?>?

    @POST("restaurant/update-open-status/{status}")
    fun openClose(@Path("status") status:String): Call<ModelStatus?>?




}