package com.otex.homamdriver.retrofit

import com.otex.homamdriver.view.home.model.ModelHomeDashBord
import com.otex.homamdriver.view.login.model.ModelLogin
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap


interface ApiCall {

    @GET("services/rest/?")
    fun getPhotos(@QueryMap requestBody: HashMap<String, String?>?): Call<String?>?

    @FormUrlEncoded
    @POST("driver/login")
    fun login(@FieldMap map: HashMap<String, String?>?): Call<ModelLogin?>?

    @GET("driver/dashboard")
    fun homeDashBord():Call<ModelHomeDashBord?>?

}