package com.otex.homamdriver.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import com.otex.homamdriver.utlitites.Constant
import com.otex.homamdriver.utlitites.DataEnum
import com.otex.homamuser.utlitites.PrefsUtil.with
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// start comment
//import okhttp3.logging.HttpLoggingInterceptor;
// end comment
class RestRetrofit private constructor() {
    var apiKey = "api-key"
    var Authorization = "Authorization"
    private val deviceKey = "device"

    private var deviceValue: String? = ""

    companion object {
        private val TAG = RestRetrofit::class.java.simpleName
        private var instance: RestRetrofit? = null
        lateinit var clientService: ApiCall


        //public final String BASE_URL = "http://192.168.1.222/";
        @SuppressLint("StaticFieldLeak")
        private var mcontext: Context? = null
        @JvmStatic
        fun getInstance(context: Context?): RestRetrofit? {
            mcontext = context
            if (instance == null) {
                instance = RestRetrofit()
            }
            return instance
        }

        fun getVestionCode(c: Context?): String {
            /*
        p40sdmkkmgjb1ggyadqz
        e4bbe5b7a4c1eb55652965aee885dd59bd2ee7f4
         */
            var v = ""
            try {
                v += c!!.packageManager
                        .getPackageInfo(c.packageName, 0).versionName
                with(c).add(DataEnum.shversionName.name, v)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            // Log.e("log",v);
            return v
        }
    }

    init {
        val builder = OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)

        builder.addInterceptor { chain ->
            val request = chain.request()
            val newRequest: Request
            val token = with(mcontext!!)["token", ""]

            if (token!!.isNotEmpty()) {

                newRequest = request.newBuilder()
                    .header(Authorization, "Bearer $token")
                        .header("Accept", "application/json")
                        .method(request.method, request.body)
                    .build()
                chain.proceed(newRequest)
            } else {
                newRequest = request.newBuilder()
                        .header("Accept", "application/json")
                        .method(request.method, request.body)
                    .build()
                chain.proceed(newRequest)
            }
        }

        val interceptor =  HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor)

        val httpClient = builder.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        clientService = retrofit.create(ApiCall::class.java)
    }

    fun getClientService(): ApiCall {
         return clientService
    }
}