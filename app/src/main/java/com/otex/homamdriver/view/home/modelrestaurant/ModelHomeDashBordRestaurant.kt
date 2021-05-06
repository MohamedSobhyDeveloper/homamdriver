package com.otex.homamdriver.view.home.modelrestaurant

import android.os.Parcel
import android.os.Parcelable

data class ModelHomeDashBordRestaurant(
    val accepted: Int,
    val canceled: Int,
    val delivered: Int,
    val pending: Int,
    val revenue: Int,
    val working_on: Int,
    val on_delivery:Int


)